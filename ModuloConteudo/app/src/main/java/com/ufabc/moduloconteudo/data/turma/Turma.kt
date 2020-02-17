package com.ufabc.moduloconteudo.data.turma

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "turma")
class Turma (
    @PrimaryKey @ColumnInfo(name = "codigo_sie")
    val codigo_sie: String,
    @ColumnInfo(name = "nome_turma")
    val nome_turma : String,
    @ColumnInfo(name = "campus")
    val campus : String,
    @ColumnInfo(name = "codigo_disciplina")
    val codigo_disciplina : String
)