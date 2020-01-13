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
        val class_code = itemView.class_code
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesListViewHolder {
        return ClassesListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.temp_aula, parent, false))
    }

    override fun getItemCount(): Int {
        return classes.size
    }

    override fun onBindViewHolder(holder: ClassesListViewHolder, position: Int) {
        val currClass = classes[position].aulasDiscente[0]
        holder.class_code.text = currClass.codigo_turma
        if(currClass.id_dia_semana != currentDay) holder.itemView.visibility = View.GONE
        else holder.itemView.visibility = View.VISIBLE
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