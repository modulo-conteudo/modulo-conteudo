package com.ufabc.moduloconteudo.act_home.tabs.archives

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.adapters.ArchiveClassArchiveListAdapter
import com.ufabc.moduloconteudo.adapters.ArchiveClassNameListAdapter
import com.ufabc.moduloconteudo.adapters.ArchiveClassNumberListAdapter
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton
import com.ufabc.moduloconteudo.adapters.ArchiveClassTypeListAdapter
import com.ufabc.moduloconteudo.data.AppDatabase
import com.ufabc.moduloconteudo.data.archives.Archive
import com.ufabc.moduloconteudo.data.archives.GeneralClass
import com.ufabc.moduloconteudo.data.archives.SingleClass

class ArchivesFragment : Fragment() {

    // Variables
    var generalClasses : List<GeneralClass>? = listOf()
    var singleClasses : List<SingleClass>? = listOf()
    var archives : List<Archive> = listOf()

    val classesNameListAdapter : ArchiveClassNameListAdapter = ArchiveClassNameListAdapter()
    val classTypeListAdapter : ArchiveClassTypeListAdapter= ArchiveClassTypeListAdapter()
    val classesNumberListAdapter: ArchiveClassNumberListAdapter = ArchiveClassNumberListAdapter()
    val classesArchiveListAdapter : ArchiveClassArchiveListAdapter = ArchiveClassArchiveListAdapter()

    var listSie = arrayListOf<String>()
    var listClass = MutableLiveData<List<GeneralClass>>()
    var deepLevel : MutableLiveData<Int> = MutableLiveData(0)

    lateinit var classClicked : GeneralClass
    var nameClicked = ""
    var typeClicked = ""
    var numberClicked = ""

    private lateinit var archivesViewModel: ArchivesViewModel
    lateinit var recyclerArchives : RecyclerView
    lateinit var btnBack : ImageButton
    lateinit var txtHistory : TextView
    lateinit var root : View

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        archivesViewModel = ViewModelProviders.of(this).get(ArchivesViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_archives, container, false)

        bindComponents(root)
        setObservers(root)
        setButtonClickEvents(root)
        ConfigurationSingleton.persistConfigModificationsOnAllViews(root, context)

        return root
    }

    override fun onResume() {
        super.onResume()
        ConfigurationSingleton.persistConfigModificationsOnAllViews(root, context)
    }

    private fun bindComponents(root: View) {
        recyclerArchives = root.findViewById(R.id.archives_recycler_archives)
        btnBack = root.findViewById(R.id.archives_btn_back)
        txtHistory = root.findViewById(R.id.archives_txt_history)
    }

    private fun setButtonClickEvents(root: View) {
        btnBack.setOnClickListener {
            if (deepLevel.value!! > 0) {
                deepLevel.value = deepLevel.value!! - 1
            }
        }
    }

    private fun setObservers(root : View) {

        deepLevel.observe(this, Observer {
            if(it == 0) setupGeneralClassAdapter(root)
            if(it == 1) setupTypeClassAdapter(root)
            if(it == 2) setupSingleClassesAdapter(root)
            if(it == 3) setupClassDocumentsAdapter(root)
            updateHistory(it)
            updateBackButtonVisibility(it)
        })

        listClass.observe(this, Observer {
            generalClasses = it
            deepLevel.value = deepLevel.value
        })

        for (it in ConfigurationSingleton.getListClass())
            listSie.add(it.discenteTurma.codigo_sie)
        AppDatabase.getInstance(root.context).generalClassDao().getClassArchives(listSie).observe(this, Observer {
            listClass.value = it
        })
    }


    private fun updateBackButtonVisibility(level: Int?) {
        if(level == 0)
            btnBack.visibility = View.INVISIBLE
        else
            btnBack.visibility = View.VISIBLE
    }

    private fun updateHistory(level: Int) {
        var txt = ""
        if(level >= 0) txt += ""
        if(level >= 1) txt += "$nameClicked/"
        if(level >= 2) txt += "$typeClicked/"
        if(level >= 3) txt += "$numberClicked/"
        txtHistory.text = txt
        context?.let { ConfigurationSingleton.VibrateCellphone(it) }
    }


    private fun setupGeneralClassAdapter(root : View) {
        recyclerArchives.adapter = classesNameListAdapter
        recyclerArchives.layoutManager = LinearLayoutManager(root.context)
        recyclerArchives.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        classesNameListAdapter.onItemClick = { generalClass, position ->
            classClicked = generalClass
            nameClicked = generalClass.name
            deepLevel.value = 1
        }

        classesNameListAdapter.setData(generalClasses)
    }

    private fun setupTypeClassAdapter(root: View) {
        recyclerArchives.adapter = classTypeListAdapter
        recyclerArchives.layoutManager = LinearLayoutManager(root.context)
        recyclerArchives.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        classTypeListAdapter.onItemClick = { classType, position ->
            typeClicked = classType
            if(classType == "Teoria")
                singleClasses = classClicked.single_class_theory
            else
                singleClasses = classClicked.single_class_practice
            deepLevel.value = 2
        }

        val l = arrayListOf<String>()
        if(classClicked.has_theory) l.add("Teoria")
        if(classClicked.has_practice) l.add("Prática")
        classTypeListAdapter.setData(l.toList())
    }

    private fun setupSingleClassesAdapter(root : View) {
        recyclerArchives.adapter = classesNumberListAdapter
        recyclerArchives.layoutManager = LinearLayoutManager(root.context)
        recyclerArchives.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        classesNumberListAdapter.onItemClick = { singleClass, position ->
            archives = singleClass.list_archive
            numberClicked = singleClass.name
            deepLevel.value = 3
        }

        classesNumberListAdapter.setData(singleClasses)
    }

    private fun setupClassDocumentsAdapter(root: View) {

        recyclerArchives.adapter = classesArchiveListAdapter
        recyclerArchives.layoutManager = LinearLayoutManager(root.context)
        recyclerArchives.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        classesArchiveListAdapter.onItemClick = { archive, position ->
            startActivity(Intent( Intent.ACTION_VIEW, Uri.parse(archive.url)))
        }

        classesArchiveListAdapter.setData(archives)
    }
}