package com.ufabc.moduloconteudo.data.archives

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

class Converters : Serializable {
    @TypeConverter
    fun fromSmallList(singleClassList : List<SingleClass>?) : String? {
        if(singleClassList == null) return null
        val type = object : TypeToken<List<SingleClass>>(){}.type
        return Gson().toJson(singleClassList, type)
    }

    @TypeConverter
    fun toSmallList (smallListString : String?) : List<SingleClass>? {
        if(smallListString == null) return null
        val type = object : TypeToken<List<SingleClass>>(){}.type
        return Gson().fromJson(smallListString, type)
    }
}