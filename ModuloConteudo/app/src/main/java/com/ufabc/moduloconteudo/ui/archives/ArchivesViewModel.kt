package com.ufabc.moduloconteudo.ui.archives

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArchivesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is archives Fragment"
    }
    val text: LiveData<String> = _text
}