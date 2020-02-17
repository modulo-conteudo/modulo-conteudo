package com.ufabc.moduloconteudo.ui.classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ufabc.moduloconteudo.data.aula.AulaRepository
import com.ufabc.moduloconteudo.data.relations.AulasDiscente

class ClassesViewModel internal constructor(aulaRepository: AulaRepository): ViewModel() {

    val mutableRa : MutableLiveData<String> = MutableLiveData()

    val classes : LiveData<List<AulasDiscente>> = Transformations.switchMap(mutableRa) {
        aulaRepository.getAulasUsingRA(it)
    }

    fun searchByRa(ra : String) {
        mutableRa.value = ra
    }
}