import sys, json
import pandas as pd

def dump_csv(in_file: str, out_file: str):
    classes = []  # "ra", "codigo_turma"
    students = [] #"ra", "nome", "sobrenome"
    students_inx = set()

    for a_class in pd.read_excel(in_file).iterrows():
        a_class = a_class[1]

        _class = {"ra"           : a_class['RA'],
                  "codigo_sie" : a_class['COD_TURMA']}

        classes.append(_class)

        if a_class['RA'] not in students_inx:
            students_inx.add(a_class['RA'])
            try:
                name = a_class['NOME_ALUNO'].strip().split(" ")
            except:
                name = ["",""]

            student = {"ra"        : a_class['RA'],
                       "nome"      : name[0],
                       "sobrenome" : " ".join(name[1:])}
            students.append(student)


    classes = json.dumps(classes, indent=2, ensure_ascii=False)
    students = json.dumps(students, indent=2, ensure_ascii=False)

    with open(out_file+"_aulas.json", "w") as f:
        f.writelines(classes)

    with open(out_file+"_alunos.json", "w") as f:
        f.writelines(students)





if __name__ == '__main__':
    in_file = sys.argv[1]
    out_file = sys.argv[2]
    dump_csv(in_file, out_file)
