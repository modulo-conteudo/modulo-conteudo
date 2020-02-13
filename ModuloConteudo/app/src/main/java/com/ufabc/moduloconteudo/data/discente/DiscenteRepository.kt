package com.ufabc.moduloconteudo.data.discente

class DiscenteRepository private constructor(private val discenteDao: DiscenteDao){

    fun getStudents() = discenteDao.getDiscentes()
    fun getStudentsWithRa(ra : String) = discenteDao.getDiscenteWithRa(ra)

    companion object {
        @Volatile private var instance: DiscenteRepository? = null
        fun getInstance(discenteDao: DiscenteDao) =
            instance ?: synchronized(this) {
                instance
                    ?: DiscenteRepository(discenteDao).also { instance = it }
            }

    }
}