package com.ufabc.moduloconteudo.act_tut

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.HomeActivity

class TutorialVideoActivity : AppCompatActivity() {
    lateinit var btn_exit : Button
    lateinit var video_view : VideoView
    lateinit var videoPath : String
    lateinit var uri : Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial_video)

        bindComponents()
        setClickEvents()
        configureVideoView()
    }

    private fun configureVideoView() {
        videoPath = "android.resource://" + packageName + "/" + R.raw.video_example
        uri = Uri.parse(videoPath)
        video_view.setVideoURI(uri)

        var media_controller = MediaController(this)
        video_view.setMediaController(media_controller)
        media_controller.setAnchorView(video_view)

        video_view.start()
    }

    private fun bindComponents() {
        btn_exit = findViewById(R.id.tut_video_btn_exit)
        video_view = findViewById(R.id.tut_video_videoview)
    }

    private fun setClickEvents() {
        btn_exit.setOnClickListener { _ ->
            exitToHomeActivity()
        }
    }

    private fun exitToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent, null)
    }

}