package com.ufabc.moduloconteudo.ui.configuration

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
    private val FS_STATUS = Pair("font_size", 14)


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
}
