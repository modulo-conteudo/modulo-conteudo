package com.ufabc.moduloconteudo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.data.ClassName
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton
import com.ufabc.moduloconteudo.data.archives.GeneralClass
import kotlinx.android.synthetic.main.item_archive_class_name.view.*

class ArchiveClassNameListAdapter : RecyclerView.Adapter<ArchiveClassNameListAdapter.ArchiveClassNameListViewHolder> () {

    var classes : List<GeneralClass> = arrayListOf()
    var onItemClick : ((GeneralClass, position : Int) -> Unit)? = null

    fun setData(s : List<GeneralClass>?) {
        classes = if(s == null) emptyList() else s
        notifyDataSetChanged()
    }

    inner class ArchiveClassNameListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val className = itemView.adapter_className
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(classes[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): ArchiveClassNameListViewHolder {
        var arch_list_root = LayoutInflater.from(parent.context).inflate(R.layout.item_archive_class_name, parent, false)
        ConfigurationSingleton.persistConfigModificationsOnAllViews(arch_list_root, null)
        return ArchiveClassNameListViewHolder(arch_list_root)
    }

    override fun getItemCount(): Int {
        return classes.size
    }

    override fun onBindViewHolder(holder: ArchiveClassNameListViewHolder, position: Int) {
        holder.className.text = classes[position].name
    }
}

