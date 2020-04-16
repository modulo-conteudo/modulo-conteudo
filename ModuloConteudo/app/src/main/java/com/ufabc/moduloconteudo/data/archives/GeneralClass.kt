package com.ufabc.moduloconteudo.data.archives

import androidx.room.*

@Entity(tableName = "class_archives")
@TypeConverters(Converters::class)
class GeneralClass (
    @PrimaryKey
    @ColumnInfo(name = "sie_code")
    val sie_code : String,

    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "has_theory")
    val has_theory : Boolean,

    @ColumnInfo(name = "has_practice")
    val has_practice : Boolean,

    @ColumnInfo (name = "single_class_theory")
    val single_class_theory : List<SingleClass>?,

    @ColumnInfo (name = "single_class_practice")
    val single_class_practice: List<SingleClass>?
)