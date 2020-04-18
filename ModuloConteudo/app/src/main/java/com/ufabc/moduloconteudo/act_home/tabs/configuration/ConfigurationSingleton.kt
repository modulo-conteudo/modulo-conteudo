package com.ufabc.moduloconteudo.act_home.tabs.configuration

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.graphics.Color
import android.graphics.ColorMatrixColorFilter
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.view.menu.MenuAdapter
import androidx.appcompat.view.menu.MenuView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.children
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.internal.NavigationMenu
import com.google.android.material.internal.NavigationSubMenu
import com.google.android.material.navigation.NavigationView
import com.ufabc.moduloconteudo.App.Companion.context
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.R.drawable.delete_icon_lgreen
import com.ufabc.moduloconteudo.data.relations.AulasDiscente
import kotlin.properties.Delegates


object ConfigurationSingleton {
    private val normalSizeFont = 16

    private var isBold:Boolean = false
    private var isHighContrast:Boolean = false

    private val font_text = arrayOf("Pequena", "Padrão", "Grande", "Maior")
    private var seekBar_positioning = 1
    private var is_vibrate_enable:Boolean = false

    private var fontSize by Delegates.notNull<Int>()
    private lateinit var fab_libras : View

    private var listClass = listOf<AulasDiscente>()

    private var defaultColorsSelected = false

    private var restartNeeded = false

    private var LGREEN = Color.parseColor("#6fbd72")

    private val NEGATIVE = floatArrayOf(
        -1.0f,
        0f,
        0f,
        0f,
        255f,
        0f,
        -1.0f,
        0f,
        0f,
        255f,
        0f,
        0f,
        -1.0f,
        0f,
        255f,
        0f,
        0f,
        0f,
        1.0f,
        0f
    )

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
            AppPreferences.MyBGColor = "#FFFFFF"
            AppPreferences.MyTxtColor = "#373737"
            AppPreferences.MyDefColor = false
        } else {
            isBold = AppPreferences.MyBoldStatus
            seekBar_positioning = AppPreferences.MyFontSizeStatus
            isHighContrast = AppPreferences.MyHighContrastStatus
            is_vibrate_enable = AppPreferences.MyVibrateOption
            defaultColorsSelected = AppPreferences.MyDefColor
        }
        fontSize = normalSizeFont + ((seekBar_positioning-1) * 3)
    }

    fun setRestartNeeded(value : Boolean) {
        restartNeeded = value
    }
    fun getRestartNeeded() : Boolean = restartNeeded

    fun setListClass(list : List<AulasDiscente>) {
        listClass = list
    }

    fun getListClass(): List<AulasDiscente> = listClass

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

    fun changeBoldness(v : Boolean) {
        this.isBold = v
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
                if(canChangeBoldness(v)) {
                    if (isBold)
                        v.typeface = Typeface.DEFAULT_BOLD
                    else
                        v.typeface = Typeface.DEFAULT
                }
                if(canChangeSize(v))
                    v.textSize = fontSize.toFloat()


                if(canChangeTVColors(v)) {
                    v.setTextColor(Color.parseColor(AppPreferences.MyTxtColor))
                }
            }
            if (!defaultColorsSelected) decideHowToChangeColors(v)
            if(AppPreferences.MyHighContrastStatus) invertColor(v)

        }
    }

    private fun invertColor(v: View) {
        if (v is MenuView || v is BottomNavigationView) {
            v.setBackgroundColor(LGREEN)

        } else if (v is MaterialButton && v.id != R.id.adapter_btnRemove && v.id != R.id.login_imgLogo) {
            v.setBackgroundColor(LGREEN)
            v.setTextColor(negative(v.currentTextColor))

        } else if(v is ImageButton) {
            v.setBackgroundColor(LGREEN)
        }

        else if(v is TextView) {
            v.setTextColor(negative(v.currentTextColor))
            if (v.text == "Módulo Conteúdo") v.setBackgroundColor(LGREEN)

        } else if (v is ConstraintLayout || v is FrameLayout) {
            v.setBackgroundColor(Color.BLACK)

        } else if (v is Drawable) {
            v.colorFilter = ColorMatrixColorFilter(NEGATIVE)

        } else if(v is NavigationView) {
            v.setBackgroundColor(LGREEN)
        }

    }

    private fun decideHowToChangeColors(v: View) {
        if (AppPreferences.MyHighContrastStatus) return
        if ((v is MaterialButton || v is TextView)) {
            v.setBackgroundColor(Color.parseColor(AppPreferences.MyBGColor))

        } else if (v is ConstraintLayout || v is FrameLayout) {
            val colorint = Color.parseColor(AppPreferences.MyBGColor)
            v.setBackgroundColor(negative(colorint))

        }
//        else if (v is NavigationView || v is Toolbar) {
//            val colorint = Color.parseColor("#1E6824")
//            v.setBackgroundColor(negative(colorint))
//        }
    }

    private fun negative(colorint: Int): Int {
        val nr = 255 - Color.red(colorint)
        val ng = 255 - Color.green(colorint)
        val nb = 255 - Color.blue(colorint)
        return Color.rgb(nr, ng, nb)
    }

    private fun canChangeBGcolor(v: View): Boolean {
        return (v is ConstraintLayout)
    }

    private fun canChangeTVColors(v: TextView): Boolean {
        return !defaultColorsSelected && !AppPreferences.MyHighContrastStatus
    }

    private fun canChangeBoldness(v: TextView): Boolean {
        return v.text != "Módulo Conteúdo"
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

    fun bigText(view: TextView, i: Int) {
        seekBar_positioning = i
        fontSize = normalSizeFont + ((seekBar_positioning-1) *3)
        AppPreferences.MyFontSizeStatus = seekBar_positioning

        view.text = "Tamanho da fonte: ${font_text[seekBar_positioning]}"
    }

//    fun setSwitchPositioning(view: View) {
//        view.font_size_text_view.text = "Tamanho da fonte: ${font_text[seekBar_positioning]}"
//        view.bold_switch.isChecked = this.isBold
//        view.vibrate_switch.isChecked = is_vibrate_enable
//        view.fab_btn_visibility.isChecked = AppPreferences.MyFabVisibility
//        view.seekBar.progress = this.seekBar_positioning
//    }

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

    fun ChangeColors(rgbColorText: String, rgbColorBg: String, defaultSelected: Boolean) {
        defaultColorsSelected = defaultSelected
        AppPreferences.MyDefColor = defaultColorsSelected
        AppPreferences.MyBGColor = rgbColorBg
        AppPreferences.MyTxtColor = rgbColorText
    }

    fun changeHighContrastOpt(b: Boolean) {
        AppPreferences.MyHighContrastStatus = b

    }
}