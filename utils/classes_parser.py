import json, xlrd, sys
import pandas as pd

#
# TEST VERSION, THINGS MUST CHANGE
#

DAY_MAP = {'segunda':0, 'terca':1, 'quarta':2, 'quinta':3,
           'sexta':4, 'sabado':5}


def get_xlsx_header(file_name: str):
    sheet = xlrd.open_workbook(file_name).sheet_by_index(0)
    header_row_indx = 0
    while sheet.row_values(header_row_indx).count('') >= 10: header_row_indx += 1
    return (sheet.row_values(header_row_indx), header_row_indx)

def get_df(file_name: str):
    header, header_indx = get_xlsx_header(file_name)

    df = pd.read_excel(file_name,  names=header)
    df = df[header_indx:]
    return df

def sanitize(string):
    return string.strip()


def parse_courses(course, tipo):
    if course[tipo] == 0:
        return []

    batch = 0
    class_infos = list(map(sanitize, course[tipo].split(',')))


    while batch <= len(class_infos)-3:
        day = sanitize(class_infos[batch].split('das')[0])
        init_time, finish_time = map(sanitize, class_infos[batch].split('das')[1].split('às'))

        # print(class_infos[batch + 1][5:].replace(" ",""))
        room = sanitize(class_infos[batch + 1][5:].replace(" ",""))

        week = sanitize(class_infos[batch + 2])
        # week = week

        try:
            prof_name = sanitize(course[f'docente {tipo}'].split(' ')[0])
        except:
            prof_name = ""

        try:
            prof_surname = " ".join(map(sanitize, course[f'docente {tipo}'].split(' ')[1:]))
        except:
            prof_surname = ""

        class_data = {
            'horario_inicio'     : init_time[:2],
            'horario_fim'        : finish_time[:2],
            'id_dia_semana'      : DAY_MAP[day.strip()],
            'id_tipo_aula'       : 0 if tipo=='teoria' else 1,
            'nome_doscente'      : '"' + prof_name + '"',
            'sobrenome_doscente' : '"' + prof_surname + '"',
            'quinzenal_1'        : 'true' if week=='quinzenal I' or week=='semanal' else 'false',
            'quinzenal_2'        : 'true' if week=='quinzenal II' or week=='semanal' else 'false',
            'sala'               : '"' + room + '"'
            }
        batch += 3

        yield class_data


def build_json(courses, indent=2):
    text = "[\n"
    sp = " "*indent
    for course in courses:
        text += sp+"{\n"

        text += ",\n".join([f"""{sp*2}"{k}": {v}""" for k,v in course.items()])
        text += "\n"

        text += sp + "},\n"

    text = text[:-2] + "\n"
    text += "]"
    return text



def get_json(df):
    courses = []
    i = 0
    for course in df.iterrows():
        course = course[1]

        # try:
        t_class = list(parse_courses(course, 'teoria'))
        p_class = list(parse_courses(course, 'prática'))
        # except:
            # print(course)
            # return ""

        for class_ in p_class:
            class_.update(
                {'codigo_turma': '"'+ str(course['Código disciplinas']) + '"',
                 'nome_turma'  : '"' +course['Disciplina'] + '"',
                 }
            )
            courses.append(class_)


        for class_ in t_class:
            class_.update(
                {'codigo_turma': '"' + str(course['Código disciplinas']) + '"',
                 'nome_turma'  : '"' + course['Disciplina'] + '"',
                 }
            )
            courses.append(class_)

        # if i == 5:
        #     break
        # i+=1

    return build_json(courses, indent=2)
    # return json.dumps(courses, indent=2)



json_data = get_json(get_df(sys.argv[1]))
# print(json_data)
with open(f"{sys.argv[1][:-5]}.json", 'w') as fl:
    fl.writelines(json_data)
