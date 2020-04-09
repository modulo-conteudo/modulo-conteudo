package com.ufabc.moduloconteudo.ui.configuration

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.graphics.Typeface
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import androidx.core.view.children
import kotlinx.android.synthetic.main.fragment_configuration.view.*
import kotlin.properties.Delegates


object ConfigurationSingleton {
    private val normalSizeFont = 16


    private var isBold:Boolean = false
    private var isHighContrast:Boolean = false

    private val font_text = arrayOf("Pequena", "Padrão", "Grande", "Maior")
    private var seekBar_positioning = 1
    private var isBigFont:Boolean = false
    private var is_vibrate_enable:Boolean = false
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
            AppPreferences.MyVibrateOption = is_vibrate_enable
        } else {
            isBold = AppPreferences.MyBoldStatus
            seekBar_positioning = AppPreferences.MyFontSizeStatus
            isHighContrast = AppPreferences.MyHighContrastStatus
            is_vibrate_enable = AppPreferences.MyVibrateOption
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


    fun persistConfigModificationsOnAllViews(view: View, c: Context?) {
        if (c != null) VibrateCellphone(c)

        for (v in view.getAllViews()) {
            if (v is TextView) {
                if (isBold)
                    v.typeface = Typeface.DEFAULT_BOLD
                else
                    v.typeface = Typeface.DEFAULT

                if (v.text != "A")
                    v.textSize = fontSize.toFloat()
            }
        }
    }

    fun VibrateCellphone(c : Context) {
        if (!is_vibrate_enable) return
        val v = c.getSystemService(VIBRATOR_SERVICE) as Vibrator?
            // Vibrate for 500 milliseconds
            // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v!!.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            //deprecated in API 26
            v!!.vibrate(50)
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
        view.vibrate_switch.isChecked = is_vibrate_enable
        this.btn_ref = view.bold_switch
        view.seekBar.progress = this.seekBar_positioning


    }

    // Torcer pra essa porcaria não ir ao infinito e além
    private fun View.getAllViews(): List<View> {
        if (this !is ViewGroup || childCount == 0) return listOf(this)
        return children
            .toList()
            .flatMap { it.getAllViews() }
            .plus(this as View)
    }

    fun changeVibrateOpt(isChecked: Boolean) {
        is_vibrate_enable = isChecked
        AppPreferences.MyVibrateOption = is_vibrate_enable
    }
}