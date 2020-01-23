package com.ufabc.moduloconteudo.ui.classes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufabc.moduloconteudo.utilities.InjectorUtils
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.adapters.ClassesListAdapter
import kotlinx.android.synthetic.main.fragment_classes.*
import kotlin.math.max
import kotlin.math.min

class ClassesFragment : Fragment() {

    // Components
    lateinit var btnPrevious : Button
    lateinit var btnNext : Button
    lateinit var txtDay : TextView
    lateinit var recyclerClasses : RecyclerView

    // Variables
    val classesListAdapter : ClassesListAdapter = ClassesListAdapter()
    var currentDay : Int = 0
    var studentRa : String = ""

    // ViewModel
    private val classesViewModel : ClassesViewModel by viewModels {
        InjectorUtils.provideTurmaViewModelFactory(requireContext())
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val root = inflater.inflate(R.layout.fragment_classes, container, false)

        val days : Array<String> = resources.getStringArray(R.array.days_week)

        bindComponents(root)
        setClickEvents(days)

        return root
    }

    private fun bindComponents(root: View) {
        btnPrevious = root.findViewById(R.id.home_btnPrevious)
        btnNext = root.findViewById(R.id.home_btnNext)
        txtDay = root.findViewById(R.id.home_txtCurrentDay)
        recyclerClasses = root.findViewById(R.id.home_recyclerClasses)
        recyclerClasses.adapter = classesListAdapter
        recyclerClasses.layoutManager = LinearLayoutManager(root.context)
    }

    private fun setClickEvents(days : Array<String>) {
        btnPrevious.setOnClickListener {
            currentDay = max(0, currentDay-1)
            updateTextCurrentDay(days[currentDay])
            updateClassesList()
        }
        btnNext.setOnClickListener {
            currentDay = min(days.size-1, currentDay+1)
            updateTextCurrentDay(days[currentDay])
            updateClassesList()
        }
    }

    private fun updateClassesList() {
        //TODO: dar um jeito de atualizar a lista conforme o dia
        Log.d("testPrint", "currDay = " + currentDay)
        classesListAdapter.changeDay(currentDay)
    }

    private fun updateTextCurrentDay(day : String) {
        home_txtCurrentDay.text = day
    }

    override fun onStart() {
        super.onStart()
        classesViewModel.classes.observe(this, Observer {

            //TODO: Verificar se existe o elemento 0
            classesListAdapter.setData(it)

            for(i in it) {
                Log.d("testPrint", it[0].aulasDiscente.size.toString())
                for (j in i.aulasDiscente)
                    Log.d("testPrint", j.codigo_turma)
            }
        })

    }

}

// Inserir estudante
//btnInsert.setOnClickListener {
//    runBlocking {
//        classesViewModel.insertDiscente(App.context, Discente(edtRa.text.toString(), "wesley", "pereira"))
//    }
//}
