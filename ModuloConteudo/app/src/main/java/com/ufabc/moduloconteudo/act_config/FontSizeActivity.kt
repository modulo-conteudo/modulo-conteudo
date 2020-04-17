package com.ufabc.moduloconteudo.act_config

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.tabs.configuration.AppPreferences
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton
import kotlinx.android.synthetic.main.fragment_configuration.view.*

class FontSizeActivity : AppCompatActivity() {
    private lateinit var seekBar: SeekBar
    private lateinit var text_view : TextView
    private lateinit var config_btn_default : MaterialButton
    private lateinit var config_fs_btn : MaterialButton
    private var size : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_font_size)

        bindComponents()
        setListeners()
    }

    private fun setListeners() {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                ConfigurationSingleton.bigText(text_view, i)
                size = i
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        config_btn_default.setOnClickListener { _ ->
            ConfigurationSingleton.bigText(text_view, 1)
            seekBar.progress = 1
        }

        config_fs_btn.setOnClickListener { _ ->
            finish()
        }

    }


    private fun bindComponents() {
        seekBar = findViewById(R.id.seekBar)
        text_view = findViewById(R.id.font_size_text_view)
        config_btn_default = findViewById(R.id.config_btn_default)
        config_fs_btn = findViewById(R.id.config_fs_btn)

        seekBar.progress = AppPreferences.MyFontSizeStatus
    }
}