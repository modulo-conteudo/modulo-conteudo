package com.ufabc.moduloconteudo.data.aula

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.ufabc.moduloconteudo.data.relations.AulasDiscente

@Dao
interface AulaDao {

   @Transaction
   @Query("SELECT * FROM discente_turma WHERE ( ra = :ra  AND codigo_turma IN (SELECT DISTINCT(codigo_turma) FROM aula))")
   fun getAulasUsingRA(ra : String) : LiveData<List<AulasDiscente>>

   @Insert
   suspend fun insertAula(aula: Aula)
}