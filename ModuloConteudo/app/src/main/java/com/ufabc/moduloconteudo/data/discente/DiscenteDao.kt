package com.ufabc.moduloconteudo.data.discente

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DiscenteDao {
    @Query("SELECT * FROM discente")
    fun getDiscentes() : LiveData<List<Discente>>

    @Query("SELECT * FROM discente WHERE ra = :ra")
    fun getDiscenteWithRa(ra : String) : LiveData<Discente>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(discentes : List<Discente>)
}