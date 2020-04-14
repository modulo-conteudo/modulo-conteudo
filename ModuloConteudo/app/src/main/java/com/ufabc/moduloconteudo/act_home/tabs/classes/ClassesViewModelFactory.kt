package com.ufabc.moduloconteudo.act_home.tabs.classes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ufabc.moduloconteudo.data.aula.AulaRepository

class ClassesViewModelFactory(
    private val repository : AulaRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ClassesViewModel(repository) as T
}