package com.ufabc.moduloconteudo.data.disciplina

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DisciplinaDao {
    @Query("SELECT * FROM disciplina")
    fun getDisciplinas() : LiveData<List<Disciplina>>

    @Insert
    suspend fun insertDisciplina(disciplina: Disciplina)
}