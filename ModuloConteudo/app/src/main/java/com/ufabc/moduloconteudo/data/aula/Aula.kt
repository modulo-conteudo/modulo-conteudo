package com.ufabc.moduloconteudo.data.aula

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "aula", primaryKeys = ["codigo_sie", "horario_inicio", "horario_fim", "id_dia_semana", "id_tipo_aula"])
class Aula (
    @ColumnInfo(name = "codigo_sie")
    val codigo_turma : String,
    @ColumnInfo(name = "horario_inicio")
    val horario_inicio : Int,
    @ColumnInfo(name = "horario_fim")
    val horario_fim : Int,
    @ColumnInfo(name = "id_dia_semana")
    val id_dia_semana : Int,
    @ColumnInfo(name = "nome_turma")
    val nome_turma: String,
    @ColumnInfo(name = "id_tipo_aula")
    val id_tipo_aula : Int,
    @ColumnInfo(name = "nome_doscente")
    val nome_doscente : String ,
    @ColumnInfo(name = "sobrenome_doscente")
    val sobrenome_doscente : String,
    @ColumnInfo(name = "quinzenal_1")
    val quinzenal_1 : Boolean,
    @ColumnInfo(name = "quinzenal_2")
    val quinzenal_2: Boolean,
    @ColumnInfo(name = "sala")
    val sala : String
)