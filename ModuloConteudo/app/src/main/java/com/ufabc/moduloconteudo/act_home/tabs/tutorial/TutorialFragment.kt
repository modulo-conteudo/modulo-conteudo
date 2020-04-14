package com.ufabc.moduloconteudo.act_home.tabs.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton

class TutorialFragment : Fragment() {

    private lateinit var tutorialViewModel: TutorialViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tutorialViewModel =
            ViewModelProviders.of(this).get(TutorialViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tutorial, container, false)

//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        tutorialViewModel.text.observe(this, Observer {
//            textView.text = it
//        })

        ConfigurationSingleton.persistConfigModificationsOnAllViews(root, context)

        return root
    }
}