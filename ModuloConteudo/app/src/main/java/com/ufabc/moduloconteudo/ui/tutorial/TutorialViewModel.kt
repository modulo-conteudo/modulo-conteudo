package com.ufabc.moduloconteudo.ui.tutorial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TutorialViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is tutorial Fragment"
    }
    val text: LiveData<String> = _text
}