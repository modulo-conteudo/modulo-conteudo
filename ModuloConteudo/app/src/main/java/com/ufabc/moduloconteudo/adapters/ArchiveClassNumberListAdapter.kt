package com.ufabc.moduloconteudo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.data.ClassNumber
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton
import kotlinx.android.synthetic.main.item_archive_class_number.view.*

class ArchiveClassNumberListAdapter : RecyclerView.Adapter<ArchiveClassNumberListAdapter.ArchiveClassNumberListViewHolder> (){

    var classNumbers : List<ClassNumber> = emptyList()
    var onItemClick : ((ClassNumber, position : Int) -> Unit)? = null

    inner class ArchiveClassNumberListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val classNumber = itemView.adapter_classNumber
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(classNumbers[adapterPosition], adapterPosition)
            }
        }
    }

    fun setData (s : MutableList<ClassNumber>){
        classNumbers = s
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): ArchiveClassNumberListViewHolder {
        val arch_list_root = LayoutInflater.from(parent.context).inflate(R.layout.item_archive_class_number, parent, false)
        ConfigurationSingleton.persistConfigModificationsOnAllViews(arch_list_root, null)
        return ArchiveClassNumberListViewHolder(arch_list_root)
    }

    override fun getItemCount(): Int {
        return classNumbers.size
    }

    override fun onBindViewHolder(holder: ArchiveClassNumberListViewHolder, position: Int) {
        holder.classNumber.text = "Aula ${classNumbers[position].id}"
    }
}