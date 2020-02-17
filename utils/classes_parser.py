import json, xlrd, sys
import pandas as pd


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

        room = sanitize(class_infos[batch + 1][5:].replace(" ",""))
        week = sanitize(class_infos[batch + 2])

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
            'id_dia_semana'      : DAY_MAP[day],
            'id_tipo_aula'       : 0 if tipo=='teoria' else 1,
            'nome_doscente'      : prof_name,
            'sobrenome_doscente' : prof_surname,
            'quinzenal_1'        : True if week=='quinzenal I' or week=='semanal' else False,
            'quinzenal_2'        : True if week=='quinzenal II' or week=='semanal' else False,
            'sala'               : room
            }
        batch += 3

        yield class_data


def get_json(df):
    courses = []
    i = 0
    for course in df.iterrows():
        course = course[1]

        t_class = list(parse_courses(course, 'teoria'))
        p_class = list(parse_courses(course, 'prática'))

        for class_ in p_class:
            class_.update(
                {'codigo_sie': str(course['Código SIE']),
                 'nome_turma'  : course['TURMA'],
                 }
            )
            courses.append(class_)


        for class_ in t_class:
            class_.update(
                {'codigo_sie': str(course['Código SIE']),
                 'nome_turma'  : course['TURMA'],
                 }
            )
            courses.append(class_)

    return json.dumps(courses, indent=2, ensure_ascii=False)



if __name__ == '__main__':
    json_data = get_json(get_df(sys.argv[1]))

    with open(f"{sys.argv[1][:-5]}.json", 'w') as fl:
        fl.writelines(json_data)
