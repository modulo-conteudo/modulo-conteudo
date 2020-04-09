package com.ufabc.moduloconteudo.ui.archives

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.adapters.ArchiveClassDocumentListAdapter
import com.ufabc.moduloconteudo.adapters.ArchiveClassNameListAdapter
import com.ufabc.moduloconteudo.adapters.ArchiveClassNumberListAdapter
import com.ufabc.moduloconteudo.data.ClassDocument
import com.ufabc.moduloconteudo.data.ClassName
import com.ufabc.moduloconteudo.data.ClassNumber
import com.ufabc.moduloconteudo.ui.configuration.ConfigurationSingleton

class ArchivesFragment : Fragment() {

    // Variables
    val classNames : MutableList<ClassName> = mutableListOf()
    var classNumbers : MutableList<ClassNumber> = mutableListOf()
    var classDocuments : MutableList<ClassDocument> = mutableListOf()
    val classesNameListAdapter : ArchiveClassNameListAdapter = ArchiveClassNameListAdapter()
    val classesNumberListAdapter: ArchiveClassNumberListAdapter = ArchiveClassNumberListAdapter()
    val classesDocumentListAdapter : ArchiveClassDocumentListAdapter = ArchiveClassDocumentListAdapter()

    var deepLevel : MutableLiveData<Int> = MutableLiveData(0)
    var histClassName : String = ""
    var histClassNumber : String = ""

    private lateinit var archivesViewModel: ArchivesViewModel
    lateinit var recyclerArchives : RecyclerView
    lateinit var btnBack : ImageButton
    lateinit var txtHistory : TextView

    init {
        // Just for tests

        classNames.add(ClassName("123123", "Algoritmos e estruturas de dados 1 A1 - diurno (Santo André)"))
        classNames.add(ClassName("123124", "Programação para dispositivos moveis B1 - diurno (Santo André)"))
        classNames.add(ClassName("123125", "Segurança de dados A2 - noturno (Santo André)"))

        classNames[0].classNumbers.add(ClassNumber(1))
        classNames[0].classNumbers.add(ClassNumber(2))
        classNames[1].classNumbers.add(ClassNumber(1))
        classNames[2].classNumbers.add(ClassNumber(1))

        classNames[0].classNumbers[0].classDocuments.add(ClassDocument("Abrir link para download", "Complexidade"))
        classNames[0].classNumbers[0].classDocuments.add(ClassDocument("Abrir link para download", "Leitura recomendada"))

        classNames[0].classNumbers[1].classDocuments.add(ClassDocument("Abrir link para download", "Teorema Mestre"))
        classNames[0].classNumbers[1].classDocuments.add(ClassDocument("Abrir link para download", "Códigos realizados em aula"))
        classNames[0].classNumbers[1].classDocuments.add(ClassDocument("Abrir link para download", "Lista 1"))

        classNames[1].classNumbers[0].classDocuments.add(ClassDocument("Abrir link para download", "Elementos básicos"))
        classNames[1].classNumbers[0].classDocuments.add(ClassDocument("Abrir link para download", "Documentação Android"))

        classNames[2].classNumbers[0].classDocuments.add(ClassDocument("Abrir link para download", "Funções hash criptográficas"))
        classNames[2].classNumbers[0].classDocuments.add(ClassDocument("Abrir link para download", "Lista 1"))
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        archivesViewModel = ViewModelProviders.of(this).get(ArchivesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_archives, container, false)

        // TODO: Seed database using JSON
        bindComponents(root)
        setObservers(root)
        setButtonClickEvents(root)
        //deepLevel.value = 0
        ConfigurationSingleton.persistConfigModificationsOnAllViews(root, context)
        return root
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
        ConfigurationSingleton.setBoldnessOnAllViews(root)
    }

    private fun setObservers(root : View) {

        deepLevel.observe(this, Observer {
            if(it == 0) setupClassNamesAdapter(root)
            if(it == 1) setupClassNumbersAdapter(root)
            if(it == 2) setupClassDocumentsAdapter(root)
            updateHistory(it)
            updateBackButtonVisibility(it)
        })

    }

    private fun updateBackButtonVisibility(level: Int?) {
        if(level == 0) btnBack.visibility = View.INVISIBLE
        else {
            btnBack.visibility = View.VISIBLE
        }
    }

    private fun updateHistory(level: Int) {
        var txt = ""
        if(level >= 0) txt += "/"
        if(level >= 1) txt += " $histClassName /"
        if(level >= 2) txt += " $histClassNumber /"
        txtHistory.text = txt
        context?.let { ConfigurationSingleton.VibrateCellphone(it) }
    }


    private fun setupClassNamesAdapter(root : View) {
        recyclerArchives.adapter = classesNameListAdapter
        recyclerArchives.layoutManager = LinearLayoutManager(root.context)
        classesNameListAdapter.onItemClick = { className, position ->
            classNumbers = className.classNumbers
            histClassName = className.nome_turma
            deepLevel.value = 1
        }

        classesNameListAdapter.setData(classNames)
    }

    private fun setupClassNumbersAdapter(root : View) {
        recyclerArchives.adapter = classesNumberListAdapter
        recyclerArchives.layoutManager = LinearLayoutManager(root.context)

        classesNumberListAdapter.onItemClick = { classNumber, position ->
            classDocuments = classNumber.classDocuments
            histClassNumber = "Aula ${classNumber.id}"
            deepLevel.value = 2
        }

        classesNumberListAdapter.setData(classNumbers)
    }

    private fun setupClassDocumentsAdapter(root: View) {

        recyclerArchives.adapter = classesDocumentListAdapter
        recyclerArchives.layoutManager = LinearLayoutManager(root.context)

        classesDocumentListAdapter.onItemClick = { classDocument, position ->
            if(position == 0)startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.ime.usp.br/~schultz/maratona-ufsc/slides/2010-04-23-teorema-mestre.pdf")))
            else Toast.makeText(root.context, "${classDocument.url}", Toast.LENGTH_SHORT).show()
        }

        classesDocumentListAdapter.setData(classDocuments)
    }


}