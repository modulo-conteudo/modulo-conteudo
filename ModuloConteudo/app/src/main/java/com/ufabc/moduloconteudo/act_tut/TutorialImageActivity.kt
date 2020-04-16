package com.ufabc.moduloconteudo.act_tut


import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.google.android.material.button.MaterialButton
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.R.drawable
import com.ufabc.moduloconteudo.act_home.HomeActivity
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton


class TutorialImageActivity : AppCompatActivity() {
    private lateinit var next_btn: AppCompatImageButton
    private lateinit var prev_btn: AppCompatImageButton
    private lateinit var exit_btn: MaterialButton
    private lateinit var imageView: ImageView
    private lateinit var text_counter: TextView

    private var array_of_screenshots = arrayOf(
        drawable.tut_1,
        drawable.tut_2,
        drawable.tut_3,
        drawable.tut_4
    )
    private val total_imgs = array_of_screenshots.size

    private var image_index : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial_images)
        ConfigurationSingleton.persistConfigModificationsOnAllViews(window.decorView.rootView, null)
//        fillArrayWithImages()
        bindComponents()
        setClickEvents()
    }


//  Código não sendo usado no momento.
//    private fun fillArrayWithImages() {
//        val drawableFiels = R.drawable::class.java.fields
//
//        drawableFiels.filter { imageIsValid(it.name) }
//
//
//        for (field in drawableFiels) {
//                array_of_screenshots.add(resources.getDrawable(field.getInt(null)))
//            }
//
//    }


    private fun imageIsValid(name: String): Boolean {
//        Log.d("SLICE: ", name+"  --  "+name.slice(IntRange(0, 3)))
        return (name.length >= 4 && name.slice(IntRange(0, 3))=="tut_")

    }


    private fun bindComponents() {
        next_btn = findViewById(R.id.tut_btn_next)
        prev_btn = findViewById(R.id.tut_btn_prev)
        exit_btn = findViewById(R.id.tut_btn_exit)
        imageView = findViewById(R.id.tut_image_view)
        text_counter = findViewById(R.id.tut_screen_shot_counter)
        text_counter.text = "1/${total_imgs}"
    }


    private fun setClickEvents() {
        next_btn.setOnClickListener { _ ->
            cicleImage(1)
        }

        prev_btn.setOnClickListener { _ ->
            cicleImage(-1)
        }

        exit_btn.setOnClickListener { _ ->
            exitToHomeActivity()
        }
    }

    private fun cicleImage(i: Int) {
        if (image_index+i >= 0 && image_index+i < total_imgs) {
            image_index += i
        } else { // TODO: TIRAR ISSO DAQUI SE NÃO QUISER UMA FILA CIRCULAR
            image_index = 0
        }

        text_counter.text = "${image_index+1}/${total_imgs}"
        imageView.setImageResource(array_of_screenshots[image_index])
    }



    private fun exitToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent, null)
    }
}