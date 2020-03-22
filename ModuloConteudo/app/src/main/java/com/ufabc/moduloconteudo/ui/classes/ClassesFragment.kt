package com.ufabc.moduloconteudo.ui.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
import com.ufabc.moduloconteudo.ui.configuration.ConfigurationSingleton
import com.ufabc.moduloconteudo.ui.configuration.ConfigurationSingleton.persistConfigModificationsOnAllViews
import com.ufabc.moduloconteudo.utilities.AppUtils
import com.ufabc.moduloconteudo.utilities.RA_EXTRA
import kotlinx.android.synthetic.main.fragment_classes.*
import java.util.*
import kotlin.math.max
import kotlin.math.min

class ClassesFragment : Fragment() {

    // Components
    lateinit var btnPrevious : ImageButton
    lateinit var btnNext : ImageButton
    lateinit var txtDay : TextView
    lateinit var recyclerClasses : RecyclerView
    lateinit var rgWeek : RadioGroup
    lateinit var rbWeek1 : RadioButton
    lateinit var rbWeek2: RadioButton
    lateinit var pbClassList : ProgressBar

    // Variables
    val classesListAdapter : ClassesListAdapter = ClassesListAdapter()
    val currentDay : MutableLiveData<Int> = MutableLiveData(0)
    var currentWeek : Int = 1
    val classes : MutableList<Aula> = mutableListOf()
    var studentRa : String = ""
    val calendar = Calendar.getInstance()
    var daysOfWeek : Array<String> = arrayOf()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val root = inflater.inflate(R.layout.fragment_classes, container, false)

        studentRa = activity?.intent?.getStringExtra(RA_EXTRA) ?: ""
        daysOfWeek = resources.getStringArray(R.array.days_week)

        bindComponents(root)
        setupClassesAdapter(root)
        setClickEvents()
        setObservers()

        ConfigurationSingleton.init(context)
        persistConfigModificationsOnAllViews(root)
        return root
    }

    override fun onStart() {
        super.onStart()

        classesViewModel.searchByRa(studentRa)
        pbClassList.visibility = View.VISIBLE
        recyclerClasses.visibility = View.GONE

        val day = calendar.get(Calendar.DAY_OF_MONTH)+7
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val weekType = AppUtils.biweekly(day, month, year)

        setCurrWeek(weekType)
        setCurrentDay(calendar.get(Calendar.DAY_OF_WEEK)-1)

        updateTextCurrentDay(currentDay.value!!)
        updateClassesList()

    }

    // ViewModel
    private val classesViewModel : ClassesViewModel by viewModels {
        InjectorUtils.provideTurmaViewModelFactory(requireContext())
    }

    private fun bindComponents(root: View) {
        btnPrevious = root.findViewById(R.id.home_btnPrevious)
        btnNext = root.findViewById(R.id.home_btnNext)
        txtDay = root.findViewById(R.id.home_txtCurrentDay)

        rgWeek = root.findViewById(R.id.home_rgWeek)
        rbWeek1 = root.findViewById(R.id.home_rbWeek1)
        rbWeek2 = root.findViewById(R.id.home_rbWeek2)

        pbClassList = root.findViewById(R.id.home_pbClassList)

        recyclerClasses = root.findViewById(R.id.home_recyclerClasses)
    }

    private fun setupClassesAdapter(root : View) {
        recyclerClasses.layoutManager = LinearLayoutManager(root.context)
        recyclerClasses.adapter = classesListAdapter
        //recyclerClasses.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))
    }

    private fun updateClassesList() {
        // TODO: Gambiarra?
        currentDay.value = currentDay.value
    }

    private fun updateTextCurrentDay(day : Int) {
        home_txtCurrentDay.text = daysOfWeek[day]
    }

    private fun setCurrWeek(weekType: Int) {
        currentWeek = weekType
        if (currentWeek == 1) rbWeek1.isChecked = true
        if (currentWeek == 2) rbWeek2.isChecked = true
    }


    private fun setCurrentDay(day: Int) {
        currentDay.value = max(0, min(daysOfWeek.size-1, day))
    }

    private fun setClickEvents() {
        btnPrevious.setOnClickListener {
            setCurrentDay(currentDay.value!!-1)
            updateTextCurrentDay(currentDay.value!!)
        }

        btnNext.setOnClickListener {
            setCurrentDay(currentDay.value!!+1)
            updateTextCurrentDay(currentDay.value!!)
        }

        rgWeek.setOnCheckedChangeListener { _, checkedId ->
            if(checkedId == rbWeek1.id) currentWeek = 1
            if(checkedId == rbWeek2.id) currentWeek = 2
            updateClassesList()
        }
    }

    private fun setObservers() {
        classesViewModel.classes.observe(this, Observer {

            classes.clear()
            for(x in it) classes.addAll(x.aulasDiscente)

            if(classes.size > 0) {
                pbClassList.visibility = View.GONE
                recyclerClasses.visibility = View.VISIBLE
            }

            updateClassesList()
        })

        currentDay.observe(this, Observer {
            val currDayClasses = mutableListOf<Aula>()
            for(cls in classes)
                if (cls.id_dia_semana == currentDay.value && (currentWeek == 1 && cls.quinzenal_1 || currentWeek == 2 && cls.quinzenal_2))
                    currDayClasses.add(cls)
            currDayClasses.sortWith(compareBy( {it.horario_inicio}, {it.codigo_sie}))
            classesListAdapter.setData(currDayClasses)
        })
    }
}

