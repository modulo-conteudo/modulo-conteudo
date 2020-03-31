package com.ufabc.moduloconteudo.ui.configuration

import android.content.ComponentCallbacks
import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import kotlinx.android.synthetic.main.fragment_configuration.view.*
import java.lang.reflect.Array
import java.util.*
import kotlin.properties.Delegates


object ConfigurationSingleton {
    private val normalSizeFont = 16


    private var isBold:Boolean = false
    private var isHighContrast:Boolean = false

    private val font_text = arrayOf("Pequena", "Padrão", "Grande", "Maior")
    private var seekBar_positioning = 1
    private var isBigFont:Boolean = false

    private lateinit var btn_ref: CompoundButton

    private var fontSize by Delegates.notNull<Int>()
//    private lateinit var preferences: Unit

    fun init(context: Context?) {
        if (context != null) {
            AppPreferences.init(context)
        }

        if(AppPreferences.isFirstRun) {
            AppPreferences.isFirstRun = false
            AppPreferences.MyBoldStatus = isBold
            AppPreferences.MyFontSizeStatus = seekBar_positioning
            AppPreferences.MyHighContrastStatus = isHighContrast
        } else {
            isBold = AppPreferences.MyBoldStatus
            seekBar_positioning = AppPreferences.MyFontSizeStatus
            isHighContrast = AppPreferences.MyHighContrastStatus
        }
        fontSize = normalSizeFont + ((seekBar_positioning-1) * 3)
    }


    fun setBoldnessOnAllViews(view: View) {
        for (v in view.getAllViews()) {
            if (v is TextView) {
                if (this.isBold) {
                    v.typeface = Typeface.DEFAULT_BOLD
                } else {
                    v.typeface = Typeface.DEFAULT
                }
            }
        }
    }

    fun changeBoldness(view: View, btn: CompoundButton) {
        this.isBold = !this.isBold
        AppPreferences.MyBoldStatus = isBold
    }

    fun changeConfigExample(txt:TextView) {
        if (this.isBold) {
            txt.typeface = Typeface.DEFAULT_BOLD
        } else {
            txt.typeface = Typeface.DEFAULT
        }
        txt.textSize = fontSize.toFloat()
    }


    fun persistConfigModificationsOnAllViews(view: View) {
        for (v in view.getAllViews()) {
            if (v is TextView) {
                if (isBold)
                    v.typeface = Typeface.DEFAULT_BOLD
                else
                    v.typeface = Typeface.DEFAULT

                if (v.text != "A" )
                    v.textSize = fontSize.toFloat()
            }
        }
    }

    fun bigText(view: View, i: Int) {
        seekBar_positioning = i
        fontSize = normalSizeFont + ((seekBar_positioning-1) *3)
        AppPreferences.MyFontSizeStatus = seekBar_positioning

        view.font_size_text_view.text = "Tamanho da fonte: ${font_text[seekBar_positioning]}"
    }

    fun setSwitchPositioning(view: View) {
        view.font_size_text_view.text = "Tamanho da fonte: ${font_text[seekBar_positioning]}"
        view.bold_switch.isChecked = this.isBold
        this.btn_ref = view.bold_switch
        view.seekBar.progress = this.seekBar_positioning

    }

    private fun View.getAllViews(): List<View> {
        if (this !is ViewGroup || childCount == 0) return listOf(this)
        return children
            .toList()
            .flatMap { it.getAllViews() }
            .plus(this as View)
    }
}