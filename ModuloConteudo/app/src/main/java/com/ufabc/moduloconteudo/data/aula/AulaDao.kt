package com.ufabc.moduloconteudo.data.aula

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ufabc.moduloconteudo.data.relations.AulasDiscente

@Dao
interface AulaDao {

   @Transaction
   @Query("SELECT * FROM discente_turma WHERE ( ra = :ra AND codigo_sie IN (SELECT DISTINCT(codigo_sie) FROM aula))")
   fun getAulasUsingRA(ra : String) : LiveData<List<AulasDiscente>>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertAll(aulas: List<Aula>)
}