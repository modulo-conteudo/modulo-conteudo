package com.ufabc.moduloconteudo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.ufabc.moduloconteudo.utilities.RA_EXTRA

class LoginActivity : AppCompatActivity() {

    lateinit var btnLogin : Button
    lateinit var edtRa : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bindComponents()
        setClickEvents()
    }

    private fun bindComponents() {
        btnLogin = findViewById(R.id.login_btnLogin)
        edtRa = findViewById(R.id.login_edtRa)
    }

    private fun setClickEvents() {
        btnLogin.setOnClickListener {
            if(verify(edtRa.text.toString().trim())) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra(RA_EXTRA, edtRa.text.toString().trim())
                startActivity(intent, null)
            } else {
                Snackbar.make(it, getString(R.string.error_ra_not_found), Snackbar.LENGTH_LONG)
                    .setAction( R.string.btn_ok, null)
                    .show()
            }
        }
    }

    private fun verify(txt_ra: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return true
    }


}
