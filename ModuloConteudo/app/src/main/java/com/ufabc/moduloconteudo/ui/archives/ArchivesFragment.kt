package com.ufabc.moduloconteudo.ui.archives

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ufabc.moduloconteudo.R

class ArchivesFragment : Fragment() {

    private lateinit var archivesViewModel: ArchivesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        archivesViewModel =
            ViewModelProviders.of(this).get(ArchivesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_archives, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        archivesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}