package com.ufabc.moduloconteudo.act_config

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.ufabc.moduloconteudo.App.Companion.context
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton


class BoldTextActivity : AppCompatActivity() {
    private lateinit var btn_yes : MaterialButton
    private lateinit var btn_no : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_bold)

        ConfigurationSingleton.persistConfigModificationsOnAllViews(window.decorView.rootView, context)
        bindComponents()
        listeners()
    }

    private fun listeners() {
        btn_yes.setOnClickListener { _ ->
            ConfigurationSingleton.changeBoldness(true)
            finish()
        }

        btn_no.setOnClickListener { _ ->
            ConfigurationSingleton.changeBoldness(false)
            finish()
        }
    }

    private fun bindComponents() {
        btn_yes = findViewById(R.id.config_bold_yes)
        btn_no = findViewById(R.id.config_bold_no)
    }
}

