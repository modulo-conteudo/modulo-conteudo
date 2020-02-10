package com.ufabc.moduloconteudo.data.aula

class AulaRepository private constructor(private val aulaDao: AulaDao){

    fun getAulasUsingRA(ra : String) = aulaDao.getAulasUsingRA(ra)

    companion object {
        @Volatile private var instance: AulaRepository? = null
        fun getInstance(aulaDao: AulaDao) =
            instance ?: synchronized(this) {
                instance
                    ?: AulaRepository(aulaDao).also { instance = it }
            }

    }
}