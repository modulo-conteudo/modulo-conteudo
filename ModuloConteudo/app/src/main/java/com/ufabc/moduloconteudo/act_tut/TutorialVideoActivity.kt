package com.ufabc.moduloconteudo.act_tut

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.HomeActivity

class TutorialVideoActivity : AppCompatActivity() {
    lateinit var btn_exit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial_video)

        bindComponents()
        setClickEvents()

    }

    private fun bindComponents() {
        btn_exit = findViewById(R.id.tut_video_btn_exit)
    }

    private fun setClickEvents() {
        btn_exit.setOnClickListener { _ ->
            exitToHomeActivity()
        }
    }

    private fun exitToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent, null)
    }

}