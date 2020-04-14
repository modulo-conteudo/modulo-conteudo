package com.ufabc.moduloconteudo.utilities

import android.content.Context
import com.ufabc.moduloconteudo.act_login.LoginViewModelFactory
import com.ufabc.moduloconteudo.data.AppDatabase
import com.ufabc.moduloconteudo.data.aula.AulaRepository
import com.ufabc.moduloconteudo.data.discente.DiscenteRepository
import com.ufabc.moduloconteudo.act_home.tabs.classes.ClassesViewModelFactory

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

    private fun getDiscenteRepository(context: Context) : DiscenteRepository {
        return DiscenteRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).discenteDao()
        )
    }

    fun provideDiscenteViewModelFactory(context: Context) : LoginViewModelFactory {
        val repository = getDiscenteRepository(context)
        return LoginViewModelFactory(
            repository
        )
    }
}