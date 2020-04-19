package com.ufabc.moduloconteudo.act_home.tabs.configuration

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private const val NAME = "configPrefFile"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // list of app specific preferences with default values
    private val FIRST_RUN = Pair("is_first_run", true)
    private val BOLD_STATUS = Pair("is_bold", false)
    private val HC_STATUS = Pair("is_high_contrast", false)
    private val FAB_LIBRAS_STATUS = Pair("is_fab_libras_visible", false)
    private val FS_STATUS = Pair("font_size_", 1)
    private val RA_VALUE  = Pair("ra_value", "")
    private val VIBRATE_STATUS = Pair("is_vibrate_enable", false)

    private val BG_COLOR_STATUS = Pair("bg_color_mc", "#FFFFFF")
    private val TXT_COLOR_STATUS = Pair("txt_color_mc", "#373737")
    private val DEF_COLOR_STATUS = Pair("def_color_mc", true)


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var isFirstRun: Boolean
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = preferences.getBoolean(FIRST_RUN.first, FIRST_RUN.second)
        // custom setter to save a preference back to preferences file
        set(value) = preferences.edit {
            it.putBoolean(FIRST_RUN.first, value)
    }

    var MyBoldStatus: Boolean
        get() = preferences.getBoolean(BOLD_STATUS.first, BOLD_STATUS.second)

        set(value) = preferences.edit {
            it.putBoolean(BOLD_STATUS.first, value)
        }

    var MyDefColor: Boolean
        get() = preferences.getBoolean(DEF_COLOR_STATUS.first, DEF_COLOR_STATUS.second)

        set(value) = preferences.edit {
            it.putBoolean(DEF_COLOR_STATUS.first, value)
        }


    var MyHighContrastStatus: Boolean
        get() = preferences.getBoolean(HC_STATUS.first, HC_STATUS.second)

        set(value) = preferences.edit {
            it.putBoolean(HC_STATUS.first, value)
        }


    var MyFontSizeStatus: Int
        get() = preferences.getInt(FS_STATUS.first, FS_STATUS.second)

        set(value) = preferences.edit {
            it.putInt(FS_STATUS.first, value)
        }

    var MyRaValue: String?
        get() = preferences.getString(RA_VALUE.first, RA_VALUE.second)

        set(value) = preferences.edit {
            it.putString(RA_VALUE.first, value)
        }

    var MyVibrateOption: Boolean
        get() = preferences.getBoolean(VIBRATE_STATUS.first, VIBRATE_STATUS.second)

        set(value) = preferences.edit {
            it.putBoolean(VIBRATE_STATUS.first, value)
        }

    var MyFabVisibility: Boolean
        get() = preferences.getBoolean(FAB_LIBRAS_STATUS.first, FAB_LIBRAS_STATUS.second)

        set(value) = preferences.edit {
            it.putBoolean(FAB_LIBRAS_STATUS.first, value)
        }

    var MyTxtColor: String?
        get() = preferences.getString(TXT_COLOR_STATUS.first, TXT_COLOR_STATUS.second)

        set(value) = preferences.edit {
            it.putString(TXT_COLOR_STATUS.first, value)
        }
    var MyBGColor: String?
        get() = preferences.getString(BG_COLOR_STATUS.first, BG_COLOR_STATUS.second)

        set(value) = preferences.edit {
            it.putString(BG_COLOR_STATUS.first, value)
        }
}
