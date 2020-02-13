package com.ufabc.moduloconteudo.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.data.aula.Aula
import com.ufabc.moduloconteudo.data.relations.AulasDiscente
import kotlinx.android.synthetic.main.temp_aula.view.*

class ClassesListAdapter : RecyclerView.Adapter<ClassesListAdapter.ClassesListViewHolder>() {
    val classes: MutableList<Aula> = mutableListOf()
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
        val currClass = classes[position]
        holder.className.text = currClass.nome_turma
        holder.hourBegin.text = currClass.horario_inicio.toString()
        holder.hourEnd.text = currClass.horario_fim.toString()
        holder.classRoom.text = currClass.sala
    }

    fun changeDay(currentDay : Int) {
        this.currentDay = currentDay
        notifyDataSetChanged()
    }

    fun setData(students: List<Aula>) {
        this.classes.clear()
        this.classes.addAll(students)
        notifyDataSetChanged()
    }

}