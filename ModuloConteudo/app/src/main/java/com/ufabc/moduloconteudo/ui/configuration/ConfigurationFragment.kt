package com.ufabc.moduloconteudo.ui.configuration

//import timber.log.Timber
//import android.R
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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


        // Listener for bold text switch
        view.bold_switch.setOnCheckedChangeListener { btn, isChecked ->
            boldOnSwitchEventAction(view, isChecked)
//            overridefonts(context, "SERIF","R.font.precious.ttf")
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

    private fun boldOnSwitchEventAction(view : View, isChecked: Boolean) {
        for (v in view.getAllViews()) {
            if (v is TextView) {
                if (isChecked) {
                    v.setTypeface(Typeface.DEFAULT_BOLD)
                } else {
                    v.setTypeface(Typeface.DEFAULT)
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel::class.java)

        // TODO: Use ViewModel


    }

    private fun View.getAllViews(): List<View> {
        if (this !is ViewGroup || childCount == 0) return listOf(this)
        return children
            .toList()
            .flatMap { it.getAllViews() }
            .plus(this as View)
    }


    // Não to usando isso pra nada, vou deixar só pra caso precise
    fun overridefonts(context: Context?, defaultFontToOverride:String, customFontFileNameInAssets:String){
        try {
            val customTypeface = Typeface.createFromAsset(context?.assets,customFontFileNameInAssets)
            val defaultTypefaceField = Typeface::class.java.getDeclaredField(defaultFontToOverride)
            defaultTypefaceField.isAccessible = true
            defaultTypefaceField.set(null,customTypeface)
            makeText(activity, "DEU BOM", Toast.LENGTH_SHORT).show()
        } catch (e:Exception){
//            makeText(activity, "${e.toString()}", Toast.LENGTH_SHORT).show()
            makeText(activity, "Cannot set font $customFontFileNameInAssets instead of $defaultFontToOverride", Toast.LENGTH_SHORT).show()
//            Timber.e("Cannot set font $customFontFileNameInAssets instead of $defaultFontToOverride")
        }
    }

}
