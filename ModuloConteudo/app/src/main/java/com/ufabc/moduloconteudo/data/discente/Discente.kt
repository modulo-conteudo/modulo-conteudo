package com.ufabc.moduloconteudo.data.discente

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "discente")
class Discente (
    @PrimaryKey @ColumnInfo(name = "ra") val ra : String,
    @ColumnInfo(name = "name") val firstName : String,
    @ColumnInfo(name = "lastName") val lastName : String
)

