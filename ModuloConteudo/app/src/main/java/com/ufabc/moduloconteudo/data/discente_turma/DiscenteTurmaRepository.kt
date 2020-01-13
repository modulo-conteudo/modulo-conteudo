package com.ufabc.moduloconteudo.data.discente_turma

class DiscenteTurmaRepository private constructor(private val discenteTurmaDao: DiscenteTurmaDao){

    fun getTurmasWithRa(ra : String) = discenteTurmaDao.getTurmasWithRa(ra)

    companion object {
        @Volatile private var instance: DiscenteTurmaRepository? = null
        fun getInstance(discenteTurmaDao: DiscenteTurmaDao) =
            instance ?: synchronized(this) {
                instance
                    ?: DiscenteTurmaRepository(discenteTurmaDao).also { instance = it }
            }

    }
}