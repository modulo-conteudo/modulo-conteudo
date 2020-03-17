package com.ufabc.moduloconteudo.ui.configuration

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children



object ConfigurationSingleton {
    private var isBold:Boolean = false
    private var isHighContrast:Boolean = false
    private var fontSize:Int = 14


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

    fun switchBoldnessOnAllViews(view: View, btn: CompoundButton) {
        this.isBold = !this.isBold
        AppPreferences.MyBoldStatus = isBold

        for (v in view.getAllViews()) {
            if (v is TextView) {
                if (isBold)
                    v.typeface = Typeface.DEFAULT_BOLD
                else
                    v.typeface = Typeface.DEFAULT
            }
        }
    }

    fun setSwitchPositioning(btn: CompoundButton) {
        btn.isChecked = this.isBold
        this.btn_ref = btn
    }

    private fun View.getAllViews(): List<View> {
        if (this !is ViewGroup || childCount == 0) return listOf(this)
        return children
            .toList()
            .flatMap { it.getAllViews() }
            .plus(this as View)
    }
}