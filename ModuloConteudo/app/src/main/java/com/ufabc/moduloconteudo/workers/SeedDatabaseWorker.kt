package com.ufabc.moduloconteudo.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.google.gson.reflect.TypeToken
import com.ufabc.moduloconteudo.data.AppDatabase
import com.ufabc.moduloconteudo.data.archives.Archive
import com.ufabc.moduloconteudo.data.aula.Aula
import com.ufabc.moduloconteudo.data.discente.Discente
import com.ufabc.moduloconteudo.data.discente_turma.DiscenteTurma
import com.ufabc.moduloconteudo.data.archives.GeneralClass
import com.ufabc.moduloconteudo.data.archives.SingleClass
import com.ufabc.moduloconteudo.utilities.DISCENTE_DATA_FILENAME
import com.ufabc.moduloconteudo.utilities.DISCENTE_TURMA_DATA_FILENAME
import com.ufabc.moduloconteudo.utilities.ARCHIVE_DATA_FILENAME
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
                    val type = object : TypeToken<List<Discente>>(){}.type
                    val discenteList : List<Discente> = Gson().fromJson(jsonReader, type)
                    database.discenteDao().insertAll(discenteList)
                }
            }

            applicationContext.assets.open(DISCENTE_TURMA_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type = object : TypeToken<List<DiscenteTurma>>() {}.type
                    val discenteTurmaList: List<DiscenteTurma> =
                        Gson().fromJson(jsonReader, type)
                    database.discenteTurmaDao().insertAll(discenteTurmaList)
                }
            }

            applicationContext.assets.open(TURMAS_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val type = object : TypeToken<List<Aula>>(){}.type
                    val aulaList : List<Aula> = Gson().fromJson(jsonReader, type)
                    database.aulaDao().insertAulaList(aulaList)
                }
            }

            applicationContext.assets.open(ARCHIVE_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use {jsonReader ->
                    val type = object : TypeToken<List<GeneralClass>>(){}.type
                    val generalClassList : List<GeneralClass> = Gson().fromJson(jsonReader, type)
                    database.generalClassDao().insertGeneralClassList(generalClassList)
                }
            }

            //val type = object : TypeToken<List<GeneralClass>>(){}.type
            //val g = listOf(
            //    GeneralClass("123", "Teste", true, true,
            //        listOf(
            //            SingleClass(1, "sTeste",
            //                listOf(
            //                    Archive("google", "www.google.com")
            //                )
            //            )
            //        ),
            //        listOf(
            //            SingleClass(1, "pTeste",
            //                listOf(
            //                    Archive("facebook", "www.facebook.com")
            //                )
            //            )
            //        )
            //    )
            //)
            //val json = Gson().toJson(g, type)
            //Log.d("debugTest", json)

            Result.success()

        } catch (ex: Exception) {
            Log.e(SeedDatabaseWorker::class.java.simpleName, "Error seeding database", ex)
            Result.failure()
        }
    }

}

