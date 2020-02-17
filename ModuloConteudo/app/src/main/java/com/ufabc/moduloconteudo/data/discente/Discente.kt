package com.ufabc.moduloconteudo.data.discente

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "discente")
class Discente (
    @PrimaryKey @ColumnInfo(name = "ra") val ra : String,
    @ColumnInfo(name = "nome") val nome: String,
    @ColumnInfo(name = "sobrenome") val sobrenome: String
)

