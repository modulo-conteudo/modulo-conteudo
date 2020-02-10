# Pré-popular banco de dados

**Objetivo:** Transformar um arquivo csv em um JSON para pré-popular o banco de dados do aplicativo.

**Parâmetros da Aula:**

01. Código da turma : String
02. Horário de início : Int
03. Horário de fim : Int
04. ID Dia Semana : Int	|Exemplo: 0 = Segunda, ..., 6 = Sábado
05. Nome da turma : String
06. ID Tipo de Aula : Int	| Exemplo: 0 = Teórica, 1 = Prática
07. Nome doscente : String
08. Sobrenome doscente : String
09. Quinzenal 1 : Boolean
10. Quinzenal 2 : Boolean
11. Sala : String

Exemplo de JSON de saida: https://github.com/android/sunflower/blob/master/app/src/main/assets/plants.json

O arquivo de entrada é enorme e tem muitos dados que não vão precisar ser utilizados. O ideal é tratá-lo exatamente do jeito que ele vem para que qualquer um possa executar o script. Caso não seja possível, acho interessante fazer um tutorialzinho pra quem for executar o script saber tratar a planilha.

## Input:

"Funções de Uma Variável", "DA1BCN0402-15SB", "SB", "terca das 14:00 às 16:00, sala A2-S104-SB, semanal , quinta das 16:00 às 18:00, sala A2-S104-SB, semanal", "ROBERTO VENEREGOLES"


## Output:

```json
[
	{
		"codigo_turma": "DA1BCN0402-15SB",
		"horario_inicio": 14,
		"horario_fim": 16,
		"id_dia_semana": 1,
		"nome_turma": "Funções de uma Variável",
		"id_tipo_aula": 0,
		"nome_doscente": "Roberto",
		"sobrenome_doscente": "Veneregoles",
		"quinzenal_1": true,
		"quinzenal_2": true,
		"sala": "A2-S104-SB"
	},
	{
		"codigo_turma": "DA1BCN0402-15SB",
		"horario_inicio": 16,
		"horario_fim": 18,
		"id_dia_semana": 3,
		"nome_turma": "Funções de uma Variável",
		"id_tipo_aula": 0,
		"nome_doscente": "Roberto",
		"sobrenome_doscente": "Veneregoles",
		"quinzenal_1": true,
		"quinzenal_2": true,
		"sala": "A2-S104-SB"
	},
]
```