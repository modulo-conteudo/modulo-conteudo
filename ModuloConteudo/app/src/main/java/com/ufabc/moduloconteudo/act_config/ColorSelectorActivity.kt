package com.ufabc.moduloconteudo.act_config

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.ufabc.moduloconteudo.App.Companion.context
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_home.HomeActivity
import com.ufabc.moduloconteudo.act_home.tabs.configuration.AppPreferences
import com.ufabc.moduloconteudo.act_home.tabs.configuration.ConfigurationSingleton

class ColorSelectorActivity : AppCompatActivity() {
    private lateinit var example_tv : TextView
    private lateinit var color_picker_text : ImageView
    private lateinit var color_picker_bg : ImageView
    private lateinit var save_btn : MaterialButton
    private lateinit var color_sel_default_btn : MaterialButton

    private var rgb_color_text = "#373737"
    private var rgb_color_bg   = "#FFFFFF"
    private var default_selected = true




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_color_selector)
        ConfigurationSingleton.persistConfigModificationsOnAllViews(window.decorView.rootView, context)


        bindComponents()
        setListeners()

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setListeners() {
        color_picker_text.setOnTouchListener(OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN || motionEvent.action == MotionEvent.ACTION_MOVE) {
                var bitmap = getBitmapFromView(color_picker_bg)

                var pixelFundo = bitmap.getPixel(motionEvent.x.toInt(), motionEvent.y.toInt())
                val r = Color.red(pixelFundo)
                val g = Color.green(pixelFundo)
                val b = Color.blue(pixelFundo)
                val hex = "#" + Integer.toHexString(pixelFundo)
                example_tv.setTextColor(Color.rgb(r, g, b))
                rgb_color_text = hex
                ConfigurationSingleton.setRestartNeeded(true)
                default_selected = false
            }
            false
        })

        color_picker_bg.setOnTouchListener(OnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN || motionEvent.action == MotionEvent.ACTION_MOVE) {
//                var bitmap = color_picker_bg.drawingCache
                var bitmap = getBitmapFromView(color_picker_bg)

                var pixelFundo = bitmap.getPixel(motionEvent.x.toInt(), motionEvent.y.toInt())
                val r = Color.red(pixelFundo)
                val g = Color.green(pixelFundo)
                val b = Color.blue(pixelFundo)
                val hex = "#" + Integer.toHexString(pixelFundo)

                example_tv.setBackgroundColor(Color.rgb(r, g, b))
                rgb_color_bg = hex
                default_selected = false
                ConfigurationSingleton.setRestartNeeded(true)
            }

            false
        })

        color_sel_default_btn.setOnClickListener { _ ->
            rgb_color_text = "#373737"
            rgb_color_bg   = "#FFFFFF"

            if (!AppPreferences.MyHighContrastStatus) {
                example_tv.setBackgroundColor(Color.parseColor(rgb_color_bg))
                example_tv.setTextColor(Color.parseColor(rgb_color_text))
            }
            default_selected = true
            ConfigurationSingleton.setRestartNeeded(!AppPreferences.MyDefColor)
            ConfigurationSingleton.ChangeColors(rgb_color_text, rgb_color_bg, default_selected)
            finish()
        }

        save_btn.setOnClickListener { _ ->
            ConfigurationSingleton.ChangeColors(rgb_color_text, rgb_color_bg, default_selected)
            finish()
        }
    }

    private fun bindComponents() {
        example_tv = findViewById(R.id.color_picker_example_text)
        color_picker_text = findViewById(R.id.color_selector_text)
        this.color_picker_bg = findViewById(R.id.color_selector_bg)
        save_btn = findViewById(R.id.color_save_btn)
        color_sel_default_btn = findViewById(R.id.color_sel_default_btn)
    }
    fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
}