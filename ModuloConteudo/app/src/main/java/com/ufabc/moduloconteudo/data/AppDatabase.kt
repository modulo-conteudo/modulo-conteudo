package com.ufabc.moduloconteudo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ufabc.moduloconteudo.data.aula.Aula
import com.ufabc.moduloconteudo.data.aula.AulaDao
import com.ufabc.moduloconteudo.data.discente.Discente
import com.ufabc.moduloconteudo.data.discente.DiscenteDao
import com.ufabc.moduloconteudo.data.discente_turma.DiscenteTurma
import com.ufabc.moduloconteudo.data.discente_turma.DiscenteTurmaDao
import com.ufabc.moduloconteudo.data.archives.GeneralClass
import com.ufabc.moduloconteudo.data.archives.GeneralClassDao
import com.ufabc.moduloconteudo.data.turma.Turma
import com.ufabc.moduloconteudo.data.turma.TurmaDao
import com.ufabc.moduloconteudo.utilities.DATABASE_NAME
import com.ufabc.moduloconteudo.workers.SeedDatabaseWorker

@Database(entities = [Discente::class, DiscenteTurma::class, Turma::class, Aula::class, GeneralClass::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun discenteDao() : DiscenteDao
    abstract fun turmaDao() : TurmaDao
    abstract fun discenteTurmaDao() : DiscenteTurmaDao
    abstract fun aulaDao() : AulaDao
    abstract fun generalClassDao() : GeneralClassDao

    companion object {
        // Create Singleton
        @Volatile private var instance : AppDatabase? = null
        fun getInstance(context: Context) : AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context : Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                }).build()
        }
    }
}