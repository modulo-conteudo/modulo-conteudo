package com.ufabc.moduloconteudo.data.archives

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ufabc.moduloconteudo.data.discente.Discente

@Dao
interface GeneralClassDao {

    @Query("SELECT * FROM class_archives")
    fun getAllClassArchives() : LiveData<List<GeneralClass>>

    @Query("SELECT * FROM class_archives WHERE sie_code IN (:sie_code)")
    fun getClassArchives(sie_code : List<String>) : LiveData<List<GeneralClass>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGeneralClassList(generalClassList : List<GeneralClass>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGeneralClass(generalClassList : GeneralClass)
}