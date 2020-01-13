package com.ufabc.moduloconteudo.ui.configuration

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ufabc.moduloconteudo.R

class ConfigurationFragment : Fragment() {

    companion object {
        fun newInstance() = ConfigurationFragment()
    }

    private lateinit var viewModel: ConfigurationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_configuration, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
