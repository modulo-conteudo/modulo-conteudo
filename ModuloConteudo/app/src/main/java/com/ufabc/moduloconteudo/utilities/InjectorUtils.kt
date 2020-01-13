package com.ufabc.moduloconteudo.utilities

import android.content.Context
import com.ufabc.moduloconteudo.data.AppDatabase
import com.ufabc.moduloconteudo.data.aula.AulaRepository
import com.ufabc.moduloconteudo.data.turma.TurmaRepository
import com.ufabc.moduloconteudo.ui.classes.ClassesViewModelFactory

object InjectorUtils {
    private fun getAulaRepository(context: Context) : AulaRepository {
        return AulaRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).aulaDao()
        )
    }

    fun provideTurmaViewModelFactory(context: Context) : ClassesViewModelFactory {
        val repository = getAulaRepository(context)
        return ClassesViewModelFactory(repository)
    }

}