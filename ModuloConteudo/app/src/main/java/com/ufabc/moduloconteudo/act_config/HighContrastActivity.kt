package com.ufabc.moduloconteudo.act_config

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.button.MaterialButton
import com.ufabc.moduloconteudo.App.Companion.context
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.HomeActivity
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationFragment
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
            ConfigurationSingleton.changeHighContrastOpt(true)
//            changeFragment(ConfigurationFragment())
            _finish()
        }

        btn_no.setOnClickListener { _ ->
            ConfigurationSingleton.changeHighContrastOpt(false)
//            changeFragment(ConfigurationFragment())
            _finish()
        }
    }

    private fun changeFragment(targetFragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, targetFragment, "fragment")
            .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    private fun _finish() {
//        val intent = Intent(context, HomeActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        startActivity(intent, null)
        finish()
    }

    private fun bindComponents() {
        btn_yes = findViewById(R.id.config_high_contrast_yes)
        btn_no = findViewById(R.id.config_high_contrast_no)
    }

}