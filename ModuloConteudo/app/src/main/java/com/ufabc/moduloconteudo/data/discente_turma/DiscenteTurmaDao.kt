package com.ufabc.moduloconteudo.data.discente_turma

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiscenteTurmaDao {

    @Query("SELECT * FROM discente_turma WHERE ra = :ra")
    fun getTurmasWithRa(ra : String) : LiveData<List<DiscenteTurma>>


    @Insert
    suspend fun insertDiscenteTurma(discenteTurma: DiscenteTurma)
}