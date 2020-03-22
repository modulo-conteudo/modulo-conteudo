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


object ConfigurationSingleton {
    private val normalSizeFont = 16
    private val bigSizeFont = 20

    private var isBold:Boolean = false
    private var isHighContrast:Boolean = false
    private var fontSize:Int = normalSizeFont
    private var isBigFont:Boolean = false

    private lateinit var btn_ref: CompoundButton

    private lateinit var preferences: Unit

    fun init(context: Context?) {
        if (context != null) {
            AppPreferences.init(context)
        }

        if(AppPreferences.isFirstRun) {
            AppPreferences.isFirstRun = false
            AppPreferences.MyBoldStatus = isBold
            AppPreferences.MyFontSizeStatus = fontSize
            AppPreferences.MyHighContrastStatus = isHighContrast
        } else {
            isBold = AppPreferences.MyBoldStatus
            fontSize = AppPreferences.MyFontSizeStatus
            isHighContrast = AppPreferences.MyHighContrastStatus
            isBigFont = if(AppPreferences.MyFontSizeStatus == normalSizeFont) false else true
        }
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

                v.textSize = fontSize.toFloat()
            }
        }
    }

    fun bigText(view: View, isBig: Boolean) {
        isBigFont = isBig
        if (isBig) {
            fontSize = bigSizeFont
        } else {
            fontSize = normalSizeFont
        }
        AppPreferences.MyFontSizeStatus = fontSize
    }

    fun setSwitchPositioning(view: View) {
        view.bold_switch.isChecked = this.isBold
        this.btn_ref = view.bold_switch

        view.big_text_switch.isChecked = this.isBigFont

    }

    private fun View.getAllViews(): List<View> {
        if (this !is ViewGroup || childCount == 0) return listOf(this)
        return children
            .toList()
            .flatMap { it.getAllViews() }
            .plus(this as View)
    }
}