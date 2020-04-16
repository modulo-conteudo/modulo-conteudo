package com.ufabc.moduloconteudo.act_home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton
import com.ufabc.moduloconteudo.data.AppDatabase
import com.ufabc.moduloconteudo.data.archives.GeneralClass
import com.ufabc.moduloconteudo.data.archives.GeneralClassDao
import com.ufabc.moduloconteudo.data.relations.AulasDiscente

class HomeActivity : AppCompatActivity() {

    val listClasses = MutableLiveData<List<AulasDiscente>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val fab : View = findViewById(R.id.fab_libras)

        ConfigurationSingleton.setFabLibrasVisibility(fab)

        fab.setOnClickListener { _ ->
            Toast.makeText(this, "Abrir video com tradução em Libras", Toast.LENGTH_SHORT)
                .show()
        }


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
}
