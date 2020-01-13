package com.ufabc.moduloconteudo.data.disciplina

class DisciplinaRepository private constructor(private val disciplinaDao: DisciplinaDao){

    fun getDisciplinas() = disciplinaDao.getDisciplinas()

    companion object {
        @Volatile private var instance: DisciplinaRepository? = null
        fun getInstance(disciplinaDao: DisciplinaDao) =
            instance ?: synchronized(this) {
                instance
                    ?: DisciplinaRepository(disciplinaDao).also { instance = it }
            }

    }
}