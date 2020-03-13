package com.ufabc.moduloconteudo.ui.configuration

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import androidx.core.view.children

object BoldConfigurationSingleton {
    var isBold = false
    init {
        //TODO: verify if some other location has changed bold configuration

    }

    fun setBoldnessOnAllViews(view: View) {
        for (v in view.getAllViews()) {
            if (v is TextView) {
                if (isBold)
                    v.setTypeface(Typeface.DEFAULT_BOLD)
                else
                    v.setTypeface(Typeface.DEFAULT)
            }
        }
    }

    fun switchBoldnessOnAllViews(view: View, btn: CompoundButton) {
        isBold = !isBold
        for (v in view.getAllViews()) {
            if (v is TextView) {
                if (isBold)
                    v.setTypeface(Typeface.DEFAULT_BOLD)
                else
                    v.setTypeface(Typeface.DEFAULT)

            }
        }
    }

    fun setSwitchPositioning(btn: CompoundButton) {
        btn.setChecked(isBold)
    }

    private fun View.getAllViews(): List<View> {
        if (this !is ViewGroup || childCount == 0) return listOf(this)
        return children
            .toList()
            .flatMap { it.getAllViews() }
            .plus(this as View)
    }

}