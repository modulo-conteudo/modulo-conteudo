package com.ufabc.moduloconteudo.data.discente_turma

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "discente_turma", primaryKeys = ["ra", "codigo_sie"])
class DiscenteTurma (
    @ColumnInfo(name = "ra")
    val ra : String,
    @ColumnInfo(name = "codigo_sie")
    val codigo_sie: String
)
