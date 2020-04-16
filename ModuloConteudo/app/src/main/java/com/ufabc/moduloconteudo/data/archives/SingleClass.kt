package com.ufabc.moduloconteudo.data.archives

import androidx.room.ColumnInfo

class SingleClass (
    val class_number: Int,
    val name : String,
    val list_archive : List<Archive>
)