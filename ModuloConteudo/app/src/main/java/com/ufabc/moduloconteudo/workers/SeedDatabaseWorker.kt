package com.ufabc.moduloconteudo.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.google.gson.reflect.TypeToken
import com.ufabc.moduloconteudo.data.AppDatabase
import com.ufabc.moduloconteudo.data.aula.Aula
import com.ufabc.moduloconteudo.data.discente.Discente
import com.ufabc.moduloconteudo.data.discente_turma.DiscenteTurma
import com.ufabc.moduloconteudo.utilities.DISCENTE_DATA_FILENAME
import com.ufabc.moduloconteudo.utilities.DISCENTE_TURMA_DATA_FILENAME
import com.ufabc.moduloconteudo.utilities.TURMAS_DATA_FILENAME
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class SeedDatabaseWorker (
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters){
    override suspend fun doWork(): Result = coroutineScope{
        try {
            // TODO: SEED DATABASE AUTOMATICALLY
            // https://github.com/android/sunflower/blob/master/app/src/main/java/com/google/samples/apps/sunflower/workers/SeedDatabaseWorker.kt
            val database = AppDatabase.getInstance(applicationContext)


            applicationContext.assets.open(DISCENTE_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->

                    val discente = object : TypeToken<List<Discente>>(){}.type
                    val discenteList : List<Discente> = Gson().fromJson(jsonReader, discente)
                    database.discenteDao().insertAll(discenteList)
                }
            }

            applicationContext.assets.open(DISCENTE_TURMA_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->

                    val discenteTurma = object : TypeToken<List<DiscenteTurma>>() {}.type
                    val discenteTurmaList: List<DiscenteTurma> =
                        Gson().fromJson(jsonReader, discenteTurma)
                    database.discenteTurmaDao().insertAll(discenteTurmaList)
                }
            }

            applicationContext.assets.open(TURMAS_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->

                    val aula = object : TypeToken<List<Aula>>(){}.type
                    val aulaList : List<Aula> = Gson().fromJson(jsonReader, aula)
                    database.aulaDao().insertAll(aulaList)
                }
            }


            Result.success()

        } catch (ex: Exception) {
            Log.e(SeedDatabaseWorker::class.java.simpleName, "Error seeding database", ex)
            Result.failure()
        }
    }

}

