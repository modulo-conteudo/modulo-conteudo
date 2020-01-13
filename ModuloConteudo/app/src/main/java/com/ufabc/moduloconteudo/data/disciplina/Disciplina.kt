package com.ufabc.moduloconteudo.data.disciplina

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disciplina")
class Disciplina (
    @PrimaryKey @ColumnInfo(name = "codigo_disciplina")
    val codigo_disciplina : String,
    @ColumnInfo(name = "nome_disciplina")
    val nome_disciplina : String,
    @ColumnInfo(name = "creditos_teoria")
    val creditos_teoria : Int,
    @ColumnInfo(name = "creditos_pratica")
    val creditos_pratica : Int,
    @ColumnInfo(name = "creditos_individual")
    val creditos_individual : Int
)