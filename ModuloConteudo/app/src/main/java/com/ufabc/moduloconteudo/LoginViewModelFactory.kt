package com.ufabc.moduloconteudo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ufabc.moduloconteudo.data.discente.DiscenteRepository

class LoginViewModelFactory (
    private val repository : DiscenteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = LoginViewModel(repository) as T
}