package com.ufabc.moduloconteudo.data.turma

class TurmaRepository private constructor(private val turmaDao : TurmaDao){
    fun getTurmas() = turmaDao.getTurmas()

    companion object {
        @Volatile private var instance: TurmaRepository? = null
        fun getInstance(turmaDao: TurmaDao) =
            instance ?: synchronized(this) {
                instance
                    ?: TurmaRepository(turmaDao).also { instance = it }
            }

    }
}