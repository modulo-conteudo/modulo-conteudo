package com.ufabc.moduloconteudo.data.aula

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.ufabc.moduloconteudo.data.relations.AulasDiscente

@Dao
interface AulaDao {

   //TODO: Implementar a busca por RA a partir da tela de login
   @Transaction
   @Query("SELECT * FROM discente_turma WHERE codigo_turma IN (SELECT DISTINCT(codigo_turma) FROM aula)")
   fun getAulasUsingRA() : LiveData<List<AulasDiscente>>

   @Insert
   suspend fun insertAula(aula: Aula)
}