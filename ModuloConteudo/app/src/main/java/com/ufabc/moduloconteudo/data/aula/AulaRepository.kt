package com.ufabc.moduloconteudo.data.aula

import androidx.lifecycle.LiveData

class AulaRepository private constructor(private val aulaDao: AulaDao){

    fun getTurmasUsingRA() = aulaDao.getAulasUsingIdDia()

    companion object {
        @Volatile private var instance: AulaRepository? = null
        fun getInstance(aulaDao: AulaDao) =
            instance ?: synchronized(this) {
                instance
                    ?: AulaRepository(aulaDao).also { instance = it }
            }

    }
}