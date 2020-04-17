package com.ufabc.moduloconteudo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton
import kotlinx.android.synthetic.main.card_aula.view.*
import kotlinx.android.synthetic.main.item_archive_class_document.view.*

class ArchiveClassTypeListAdapter : RecyclerView.Adapter<ArchiveClassTypeListAdapter.ArchiveClassDocumentListViewHolder> () {

    var classDocuments : List<String> = emptyList()
    var onItemClick : ((String, posisiton : Int) -> Unit)? = null

    inner class ArchiveClassDocumentListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val description = itemView.adapter_classType

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(classDocuments[adapterPosition], adapterPosition)
            }
        }
    }

    fun setData(s : List<String>) {
        classDocuments = s
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): ArchiveClassDocumentListViewHolder {
        var arch_root = LayoutInflater.from(parent.context).inflate(R.layout.item_archive_class_type, parent, false)
        ConfigurationSingleton.persistConfigModificationsOnAllViews(arch_root, null)
        return ArchiveClassDocumentListViewHolder(arch_root)
    }

    override fun getItemCount(): Int {
        return classDocuments.size
    }

    override fun onBindViewHolder(holder: ArchiveClassDocumentListViewHolder, position: Int) {
        holder.description.text = classDocuments[position]
    }
}
