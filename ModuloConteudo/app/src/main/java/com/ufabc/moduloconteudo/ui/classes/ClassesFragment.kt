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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufabc.moduloconteudo.utilities.InjectorUtils
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.adapters.ClassesListAdapter
import com.ufabc.moduloconteudo.data.aula.Aula
import com.ufabc.moduloconteudo.utilities.RA_EXTRA
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
    val currentDay : MutableLiveData<Int> = MutableLiveData(0)
    val classes : MutableList<Aula> = mutableListOf()
    var studentRa : String = ""

    // ViewModel
    private val classesViewModel : ClassesViewModel by viewModels {
        InjectorUtils.provideTurmaViewModelFactory(requireContext())
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val root = inflater.inflate(R.layout.fragment_classes, container, false)

        val days : Array<String> = resources.getStringArray(R.array.days_week)

        // Pegar intent de RA e mandar para viewModel atualizar database
        studentRa = activity?.intent?.getStringExtra(RA_EXTRA) ?: ""
        Log.d("testPrint", studentRa)
        classesViewModel.searchByRa(studentRa)

        bindComponents(root)
        setClickEvents(days)
        setObservers()

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
            currentDay.value = max(0, currentDay.value!!-1)
            updateTextCurrentDay(days[currentDay.value!!])
        }
        btnNext.setOnClickListener {
            currentDay.value = min(days.size-1, currentDay.value!!+1)
            updateTextCurrentDay(days[currentDay.value!!])
        }
    }

    private fun updateTextCurrentDay(day : String) {
        home_txtCurrentDay.text = day
    }

    private fun setObservers() {
        classesViewModel.classes.observe(this, Observer {

            classes.clear()
            for(x in it)
                classes.addAll(x.aulasDiscente)
            currentDay.value = currentDay.value
            Log.d("testPrint", classes.size.toString())

        })

        currentDay.observe(this, Observer {
            val currDayClasses = mutableListOf<Aula>()
            for(cls in classes)
                if (cls.id_dia_semana == currentDay.value)
                    currDayClasses.add(cls)
            classesListAdapter.setData(currDayClasses)
        })
    }
}

/***********************
// Inserir estudante
btnInsert.setOnClickListener {
    runBlocking {
        classesViewModel.insertDiscente(App.context, Discente(edtRa.text.toString(), "wesley", "pereira"))
    }
}
 ********************/
