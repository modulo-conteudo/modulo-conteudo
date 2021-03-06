package com.ufabc.moduloconteudo.act_home

import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton
import com.ufabc.moduloconteudo.data.relations.AulasDiscente


class HomeActivity : AppCompatActivity() {

    private var dx = 0.0f
    private var dy = 0.0f
    val listClasses = MutableLiveData<List<AulasDiscente>>()
    lateinit var fab : FloatingActionButton
    lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        toolbar = findViewById(R.id.toolbar)
        toolbar.menu.getItem(0).isVisible = false

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        ConfigurationSingleton.setFabLibrasVisibility(toolbar.menu.getItem(0))

        setClickListeners()

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_classes,
                R.id.navigation_archives,
                R.id.navigation_tutorial,
                R.id.navigation_configuration
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        //FIXME: Pegar informações da tela condigurações
        navView.itemIconSize = 120

    }

    override fun onResume() {
        super.onResume()
        ConfigurationSingleton.persistConfigModificationsOnAllViews(window.decorView.rootView, null)
    }

    private fun setClickListeners() {

    }
}
