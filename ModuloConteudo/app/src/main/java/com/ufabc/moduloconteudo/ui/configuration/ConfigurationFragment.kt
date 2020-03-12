package com.ufabc.moduloconteudo.ui.configuration

import android.content.Context
import android.graphics.Typeface
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
//import timber.log.Timber
import com.ufabc.moduloconteudo.R
import kotlinx.android.synthetic.main.fragment_configuration.view.*
import android.widget.Toast.makeText as makeText

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


        // Listener for bold text switch
        view.bold_switch.setOnCheckedChangeListener { btn, isChecked ->
            boldOnSwitchEventAction(btn, isChecked)
            overridefonts(context, "font", "font")
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

    private fun boldOnSwitchEventAction(btn : CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            makeText(activity, "Switch1:ON", Toast.LENGTH_SHORT).show()

        } else  {
            makeText(activity, "Switch1:OFF", Toast.LENGTH_SHORT).show()


        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel::class.java)

        // TODO: Use ViewModel


    }

    fun overridefonts(context: Context?, defaultFontToOverride:String, customFontFileNameInAssets:String){
        try {
            val customTypeface = Typeface.createFromAsset(context.assets,customFontFileNameInAssets)
            val defaultTypefaceField = Typeface::class.java.getDeclaredField(defaultFontToOverride)
            defaultTypefaceField.isAccessible = true
            defaultTypefaceField.set(null,customTypeface)
        }catch (e:Exception){
//            Timber.e("Cannot set font $customFontFileNameInAssets instead of $defaultFontToOverride")
        }
    }

}
