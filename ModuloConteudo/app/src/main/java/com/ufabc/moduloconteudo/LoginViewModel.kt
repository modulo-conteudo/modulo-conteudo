package com.ufabc.moduloconteudo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ufabc.moduloconteudo.data.discente.Discente
import com.ufabc.moduloconteudo.data.discente.DiscenteRepository

class LoginViewModel internal constructor(discenteRepository: DiscenteRepository) : ViewModel(){

    val mutableRa : MutableLiveData<String> = MutableLiveData()
    val student : LiveData<Discente> = Transformations.switchMap(mutableRa) {
        discenteRepository.getStudentsWithRa(it)
    }

    fun searchByRa(ra : String) {
        mutableRa.value = ra
    }
}