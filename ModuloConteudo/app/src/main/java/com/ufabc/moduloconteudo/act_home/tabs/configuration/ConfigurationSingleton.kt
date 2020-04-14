package com.ufabc.moduloconteudo.act_home.tabs.configuration

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
import com.ufabc.moduloconteudo.R
import kotlinx.android.synthetic.main.fragment_configuration.view.*
import kotlin.properties.Delegates


object ConfigurationSingleton {
    private val normalSizeFont = 16


    private var isBold:Boolean = false
    private var isHighContrast:Boolean = false

    private val font_text = arrayOf("Pequena", "Padrão", "Grande", "Maior")
    private var seekBar_positioning = 1
    private var is_vibrate_enable:Boolean = false

    private var fontSize by Delegates.notNull<Int>()
    lateinit private var fab_libras : View

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
            AppPreferences.MyRaValue = ""
            AppPreferences.MyFabVisibility = false
        } else {
            isBold = AppPreferences.MyBoldStatus
            seekBar_positioning = AppPreferences.MyFontSizeStatus
            isHighContrast = AppPreferences.MyHighContrastStatus
            is_vibrate_enable = AppPreferences.MyVibrateOption
        }
        fontSize = normalSizeFont + ((seekBar_positioning-1) * 3)
    }

    fun setRA(ra : String) {
        AppPreferences.MyRaValue = ra
    }

    fun getRA(): String? = AppPreferences.MyRaValue

    fun setFabLibrasVisibility(fab: View) {
        fab_libras = fab
        if (!AppPreferences.MyFabVisibility)
            fab.visibility = View.GONE
    }

    fun setBoldnessOnAllViews(view: View) {
        for (v in view.getAllViews()) {
            if (v is TextView) {
                if (this.isBold)
                    v.typeface = Typeface.DEFAULT_BOLD
                else
                    v.typeface = Typeface.DEFAULT
            }
        }
    }

    fun changeBoldness(view: View, btn: CompoundButton) {
        this.isBold = !this.isBold
        AppPreferences.MyBoldStatus = isBold
    }

    fun changeFabVisibility(isChecked: Boolean) {
        AppPreferences.MyFabVisibility = isChecked

        if (isChecked)
            fab_libras.visibility = View.VISIBLE
        else
            fab_libras.visibility = View.GONE
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
                if(canChangeSize(v))
                    v.textSize = fontSize.toFloat()
            }
        }
    }

    private fun canChangeSize(v: TextView): Boolean {
        return v.id != R.id.big_a && v.id != R.id.lower_a
    }


    fun VibrateCellphone(c : Context) {
        if (!is_vibrate_enable) return
        val v = c.getSystemService(VIBRATOR_SERVICE) as Vibrator?
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
        view.fab_btn_visibility.isChecked = AppPreferences.MyFabVisibility
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