package com.ufabc.moduloconteudo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.data.aula.Aula
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton
import com.ufabc.moduloconteudo.data.AppDatabase
import kotlinx.android.synthetic.main.card_aula.view.*
import kotlinx.coroutines.runBlocking

class ClassesListAdapter : RecyclerView.Adapter<ClassesListAdapter.ClassesListViewHolder>() {
    val classes: MutableList<Aula> = mutableListOf()

    class ClassesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val className = itemView.adapter_className
        val classTime = itemView.adapter_classTime
        val classRoom = itemView.adapter_classroom
        val teacherName = itemView.adapter_teacherName
        val classType = itemView.adapter_classType
        val btnRemove = itemView.adapter_btnRemove
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesListViewHolder {
        val card_root = LayoutInflater.from(parent.context).inflate(R.layout.card_aula, parent, false)
        ConfigurationSingleton.persistConfigModificationsOnAllViews(card_root, null)
        return ClassesListViewHolder(card_root)
    }

    override fun getItemCount(): Int {
        return classes.size
    }

    override fun onBindViewHolder(holder: ClassesListViewHolder, position: Int) {
        val currClass = classes[position]
        val time : String = currClass.horario_inicio.toString() + "h às " + currClass.horario_fim.toString() + "h"
        holder.className.text = currClass.nome_turma
        holder.classTime.text = time
        holder.classType.text = if (currClass.id_tipo_aula == 0) "TEORIA" else "PRÁTICA"
        holder.teacherName.text = (currClass.nome_doscente + " " + currClass.sobrenome_doscente).toUpperCase()
        holder.classRoom.text = currClass.sala.toUpperCase()

        holder.btnRemove.setOnClickListener {
            MaterialAlertDialogBuilder(it.context)
                .setTitle("EXCLUIR AULA")
                .setMessage("Deseja excluir a aula ${currClass.nome_turma}?")
                .setPositiveButton("SIM"){_, _ ->
                    runBlocking {
                        AppDatabase.getInstance(it.context).aulaDao().deleteAula(currClass)
                    }
                }
                .setNegativeButton("NÃO"){_, _ -> }
                .show()
        }
    }

    fun setData(students: List<Aula>) {
        this.classes.clear()
        this.classes.addAll(students)
        notifyDataSetChanged()
    }

}