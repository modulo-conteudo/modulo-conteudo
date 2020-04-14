package com.ufabc.moduloconteudo.act_home.tabs.tutorial

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton
import com.ufabc.moduloconteudo.act_login.LoginActivity
import com.ufabc.moduloconteudo.act_tut.TutorialImageActivity
import kotlinx.android.synthetic.main.fragment_tutorial.view.*


class TutorialFragment : Fragment() {

    private lateinit var tutorialViewModel: TutorialViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tutorialViewModel =
            ViewModelProviders.of(this).get(TutorialViewModel::class.java)
        return inflater.inflate(R.layout.fragment_tutorial, container, false)

//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        tutorialViewModel.text.observe(this, Observer {
//            textView.text = it
//        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ConfigurationSingleton.persistConfigModificationsOnAllViews(view, context)

        view.tut_btn_slide.setOnClickListener { _ ->
            startTutorialImageActivity()
        }


    }

    private fun startTutorialImageActivity() {
        val intent = Intent(context, TutorialImageActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent, null)
        activity?.finish()
    }
}
