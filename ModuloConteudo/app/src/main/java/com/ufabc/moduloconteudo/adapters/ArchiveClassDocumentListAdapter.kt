package com.ufabc.moduloconteudo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.data.ClassDocument
import com.ufabc.moduloconteudo.ui.configuration.BoldConfigurationSingleton
import kotlinx.android.synthetic.main.item_archive_class_document.view.*

class ArchiveClassDocumentListAdapter : RecyclerView.Adapter<ArchiveClassDocumentListAdapter.ArchiveClassDocumentListViewHolder> () {

    var classDocuments : List<ClassDocument> = emptyList()
    var onItemClick : ((ClassDocument, posisiton : Int) -> Unit)? = null

    inner class ArchiveClassDocumentListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val description = itemView.adapter_classDocument

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(classDocuments[adapterPosition], adapterPosition)
            }
        }
    }

    fun setData(s : MutableList<ClassDocument>) {
        classDocuments = s
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): ArchiveClassDocumentListViewHolder {
        var arch_root = LayoutInflater.from(parent.context).inflate(R.layout.item_archive_class_document, parent, false)
        BoldConfigurationSingleton.setBoldnessOnAllViews(arch_root)
        return ArchiveClassDocumentListViewHolder(arch_root)
    }

    override fun getItemCount(): Int {
        return classDocuments.size
    }

    override fun onBindViewHolder(holder: ArchiveClassDocumentListViewHolder, position: Int) {
        holder.description.text = classDocuments[position].description
    }
}
