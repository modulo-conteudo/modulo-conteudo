package com.ufabc.moduloconteudo.ui.configuration

//import timber.log.Timber
//import android.R
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_configuration.view.*
import com.ufabc.moduloconteudo.R

//import android.widget.Toast.makeText as makeText1

class ConfigurationFragment : Fragment() {

    companion object {
        fun newInstance() = ConfigurationFragment()
    }

    private lateinit var viewModel: ConfigurationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view : View = inflater.inflate(R.layout.fragment_configuration, container, false)
        // This sets the default boldness of a view in onCreate time
        BoldConfigurationSingleton.setBoldnessOnAllViews(view)
        BoldConfigurationSingleton.setSwitchPositioning(view.bold_switch)

        // Listener for bold text switch on runtime
        view.bold_switch.setOnCheckedChangeListener { btn, _ ->
            BoldConfigurationSingleton.switchBoldnessOnAllViews(view, btn)
        }

        // Listener for high contrast text switch
        view.high_contrast_switch.setOnCheckedChangeListener { btn, isChecked ->
            val message = if (isChecked) "Switch1:ON" else "Switch1:OFF"
            makeText(activity, message, Toast.LENGTH_SHORT).show()
        }

        // Listener for big text switch
        view.big_text_switch.setOnCheckedChangeListener { btn, isChecked ->
            val message = if (isChecked) "Switch1:ON" else "Switch1:OFF"
            makeText(activity, message, Toast.LENGTH_SHORT).show()
        }

        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel::class.java)

        // TODO: Use ViewModel


    }
}
