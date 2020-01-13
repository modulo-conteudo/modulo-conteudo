package com.ufabc.moduloconteudo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    lateinit var login_btnLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bindComponents()
        setClickEvents()
    }

    private fun bindComponents() {
        login_btnLogin = findViewById(R.id.login_btnLogin)
    }

    private fun setClickEvents() {
        login_btnLogin.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java), null)
        }
    }

}
