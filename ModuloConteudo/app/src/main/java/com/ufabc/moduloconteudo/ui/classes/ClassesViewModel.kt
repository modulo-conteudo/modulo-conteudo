package com.ufabc.moduloconteudo.ui.classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ufabc.moduloconteudo.data.aula.AulaRepository
import com.ufabc.moduloconteudo.data.relations.AulasDiscente

class ClassesViewModel internal constructor(aulaRepository: AulaRepository): ViewModel() {

    val classes : LiveData<List<AulasDiscente>> = aulaRepository.getTurmasUsingRA()


    //private val _text = MutableLiveData<String>().apply {
    //    value = "This is home Fragment"
    //}
    //val text: LiveData<String> = _text
}