package com.ufabc.moduloconteudo.act_config

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ufabc.moduloconteudo.App.Companion.context
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.tabs.configuration.AppPreferences
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton


class HighContrastActivity : AppCompatActivity() {
    private lateinit var btn_yes : MaterialButton
    private lateinit var btn_no : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_high_contrast)

        ConfigurationSingleton.persistConfigModificationsOnAllViews(window.decorView.rootView, context)
        bindComponents()
        listeners()
    }

    private fun listeners() {
        btn_yes.setOnClickListener { _ ->
            ConfigurationSingleton.setRestartNeeded(!AppPreferences.MyHighContrastStatus)
            ConfigurationSingleton.changeHighContrastOpt(true)
            finish()
        }

        btn_no.setOnClickListener { _ ->
            ConfigurationSingleton.setRestartNeeded(AppPreferences.MyHighContrastStatus)
            ConfigurationSingleton.changeHighContrastOpt(false)
            finish()
        }
    }


    private fun bindComponents() {
        btn_yes = findViewById(R.id.config_high_contrast_yes)
        btn_no = findViewById(R.id.config_high_contrast_no)
    }

}