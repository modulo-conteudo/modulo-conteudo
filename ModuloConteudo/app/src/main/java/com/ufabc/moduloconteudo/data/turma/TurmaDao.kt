package com.ufabc.moduloconteudo.data.turma

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TurmaDao {
    @Query("SELECT * FROM turma")
    fun getTurmas() : LiveData<List<Turma>>


    @Insert
    suspend fun insertTurma(turma: Turma)
}