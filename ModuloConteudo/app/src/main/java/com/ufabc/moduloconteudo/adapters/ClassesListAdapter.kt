package com.ufabc.moduloconteudo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.data.relations.AulasDiscente
import kotlinx.android.synthetic.main.temp_aula.view.*

class ClassesListAdapter : RecyclerView.Adapter<ClassesListAdapter.ClassesListViewHolder>() {
    private var classes: List<AulasDiscente> = listOf()
    private var currentDay : Int = 0

    class ClassesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val className = itemView.adapter_className
        val hourBegin = itemView.adapter_hourBegin
        val hourEnd = itemView.adapter_hourEnd
        val classRoom = itemView.adapter_classroom
        val campus = itemView.adapter_campus
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesListViewHolder {
        return ClassesListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.temp_aula, parent, false))
    }

    override fun getItemCount(): Int {
        return classes.size
    }

    override fun onBindViewHolder(holder: ClassesListViewHolder, position: Int) {
        val currClass = classes[position].aulasDiscente[0]
        holder.className.text = currClass.nome_turma
        holder.hourBegin.text = currClass.horario_inicio.toString()
        holder.hourEnd.text = currClass.horario_fim.toString()
        holder.classRoom.text = currClass.sala
        setVisibility(currClass.id_dia_semana, holder.itemView)

    }

    private fun setVisibility(idDiaSemana: Int, itemView: View) {
        if(idDiaSemana == currentDay) {
            itemView.visibility = View.VISIBLE
        } else {
            itemView.visibility = View.GONE
        }
    }

    fun changeDay(currentDay : Int) {
        this.currentDay = currentDay
        notifyDataSetChanged()
    }

    fun setData(students: List<AulasDiscente>) {
        this.classes = students
        notifyDataSetChanged()
    }

}