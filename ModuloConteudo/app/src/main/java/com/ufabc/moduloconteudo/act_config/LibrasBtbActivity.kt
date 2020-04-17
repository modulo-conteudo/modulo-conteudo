package com.ufabc.moduloconteudo.act_config

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton

class LibrasBtbActivity : AppCompatActivity() {
    private lateinit var btn_yes : MaterialButton
    private lateinit var btn_no : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_libras_btn)

        bindComponents()
        listeners()
    }

    private fun listeners() {
        btn_yes.setOnClickListener { _ ->
            ConfigurationSingleton.changeFabVisibility(true)
            finish()
        }

        btn_no.setOnClickListener { _ ->
            ConfigurationSingleton.changeFabVisibility(false)
            finish()
        }
    }

    private fun bindComponents() {
        btn_yes = findViewById(R.id.config_libras_yes)
        btn_no = findViewById(R.id.config_libras_no)
    }

}