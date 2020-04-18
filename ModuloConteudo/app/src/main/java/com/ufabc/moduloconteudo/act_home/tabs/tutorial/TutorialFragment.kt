package com.ufabc.moduloconteudo.act_home.tabs.tutorial

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton
import com.ufabc.moduloconteudo.act_login.LoginActivity
import com.ufabc.moduloconteudo.act_tut.TutorialImageActivity
import com.ufabc.moduloconteudo.act_tut.TutorialVideoActivity
import kotlinx.android.synthetic.main.fragment_tutorial.view.*


class TutorialFragment : Fragment() {

    private lateinit var tutorialViewModel: TutorialViewModel
    private lateinit var btn_slides : Button
    private lateinit var btn_video  : Button
    private lateinit var root : View


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tutorialViewModel =
            ViewModelProviders.of(this).get(TutorialViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_tutorial, container, false)

        bindComponents(root)
        createListeners()
        return root
    }

    override fun onResume() {
        super.onResume()
        ConfigurationSingleton.persistConfigModificationsOnAllViews(root, context)
    }

    private fun createListeners() {
        btn_slides.setOnClickListener { _ ->
            startTutorialImageActivity()
        }

        btn_video.setOnClickListener { _ ->
            startTutorialVideoActivity()
        }
    }

    private fun bindComponents(v : View) {
        btn_slides = v.tut_btn_slide
        btn_video = v.tut_btn_video

    }


    private fun startTutorialImageActivity() {
        val intent = Intent(context, TutorialImageActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent, null)
//        activity?.finish()
    }

    private fun startTutorialVideoActivity() {
        val intent = Intent(context, TutorialVideoActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent, null)
//        activity?.finish()
    }

}
