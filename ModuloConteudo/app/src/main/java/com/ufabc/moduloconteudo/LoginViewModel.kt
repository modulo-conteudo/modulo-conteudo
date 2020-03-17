package com.ufabc.moduloconteudo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ufabc.moduloconteudo.data.AppDatabase
import com.ufabc.moduloconteudo.data.discente.Discente
import com.ufabc.moduloconteudo.data.discente.DiscenteRepository
import com.ufabc.moduloconteudo.ui.configuration.ConfigurationSingleton

class LoginViewModel internal constructor(discenteRepository: DiscenteRepository) : ViewModel(){

    val mutableRa : MutableLiveData<String> = MutableLiveData()
    val student : LiveData<Discente> = Transformations.switchMap(mutableRa) {
        discenteRepository.getStudentsWithRa(it)
    }


    fun searchByRa(ra : String) {
        mutableRa.value = ra
    }


}