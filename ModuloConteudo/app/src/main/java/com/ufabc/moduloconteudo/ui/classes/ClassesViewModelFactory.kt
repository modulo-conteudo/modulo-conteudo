package com.ufabc.moduloconteudo.ui.classes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ufabc.moduloconteudo.data.aula.AulaRepository
import com.ufabc.moduloconteudo.data.discente.DiscenteRepository
import com.ufabc.moduloconteudo.data.discente_turma.DiscenteTurmaRepository
import com.ufabc.moduloconteudo.data.turma.TurmaRepository

class ClassesViewModelFactory(
    private val repository : AulaRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ClassesViewModel(repository) as T
}