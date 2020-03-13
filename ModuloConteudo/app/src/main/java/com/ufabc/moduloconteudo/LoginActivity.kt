package com.ufabc.moduloconteudo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.ufabc.moduloconteudo.utilities.InjectorUtils
import com.ufabc.moduloconteudo.utilities.RA_EXTRA

class LoginActivity : AppCompatActivity() {

    lateinit var btnLogin : Button
    lateinit var edtRa : TextInputEditText
    lateinit var layoutLogin : ConstraintLayout
    val studentRa : MutableLiveData<String> = MutableLiveData()

    private val loginViewModel : LoginViewModel by viewModels {
        InjectorUtils.provideDiscenteViewModelFactory(App.context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        bindComponents()
        setClickEvents()
        setObservers()
    }

    private fun setObservers() {
        studentRa.observe(this, Observer {
            if(it.isEmpty()) {
                Snackbar.make(findViewById(R.id.login_layout), getString(R.string.error_ra_not_found), Snackbar.LENGTH_LONG)
                    .setAction(R.string.btn_ok, null)
                    .show()
            } else {
                openHomeActivity()
            }
        })

        loginViewModel.student.observe(this, Observer {
            if(it != null) studentRa.value = it.ra
            else studentRa.value = ""
        })
    }

    private fun bindComponents() {
        btnLogin = findViewById(R.id.login_btnLogin)
        edtRa = findViewById(R.id.login_edtRa)
    }

    private fun setClickEvents() {
        btnLogin.setOnClickListener {
            val ra = edtRa.text.toString()
            loginViewModel.searchByRa(ra)
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun openHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(RA_EXTRA, studentRa.value)
        startActivity(intent, null)
    }



}
