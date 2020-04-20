import json, xlrd, sys
import pandas as pd


DAY_MAP = {'domingo':0,'segunda':1, 'terca':2, 'quarta':3, 'quinta':4,
           'sexta':5, 'sabado':6}


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
            #'horario_inicio'     : init_time[:2],
            #'horario_fim'        : finish_time[:2],
            #'id_dia_semana'      : DAY_MAP[day],
            #'id_tipo_aula'       : 0 if tipo=='teoria' else 1,
            #'nome_doscente'      : prof_name,
            #'sobrenome_doscente' : prof_surname,
            #'quinzenal_1'        : True if week=='quinzenal I' or week=='semanal' else False,
            #'quinzenal_2'        : True if week=='quinzenal II' or week=='semanal' else False,
            #'sala'               : room
            }
        batch += 3

        yield class_data


def get_json(df):
    list_courses = []
    set_sie_code = set()
    dict_name = dict()
    set_has_theory = set()
    set_has_practice = set()

    i = 0
    for course in df.iterrows():
        course = course[1]
        sie_code = str(course['Código SIE'])
        name_class = course['Disciplina']

        dict_name[sie_code] = name_class
        set_sie_code.add(sie_code)

        t_class = list(parse_courses(course, 'teoria'))
        p_class = list(parse_courses(course, 'prática'))

        for class_ in p_class:
            set_has_practice.add(sie_code)

        for class_ in t_class:
            set_has_theory.add(sie_code)
                

    archive_1 = {
            "name": "Arquivo exemplo",
            "url": "www.ufabc.edu.br"
        }
    for scode in set_sie_code:
        name = dict_name[scode]
        ht = scode in set_has_theory
        hp = scode in set_has_practice

        type_data_1 = {
                "class_number": 1,
                "name": "Aula 1: Introdução à " + name,
                "list_archive": [archive_1]
                }

        class_data = {
                "sie_code" : scode,
                "name" : name,
                "has_theory" : ht,
                "has_practice" : hp,
                "single_class_theory": [type_data_1] if ht else None,
                "single_class_practice": [type_data_1] if hp else None,
                }
        list_courses.append(class_data)


    return json.dumps(list_courses, indent=2, ensure_ascii=False)



if __name__ == '__main__':
    json_data = get_json(get_df(sys.argv[1]))

    with open(f"{sys.argv[1][:-5]}.json", 'w') as fl:
        fl.writelines(json_data)
