package com.ufabc.moduloconteudo.ui.configuration


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ufabc.moduloconteudo.LoginActivity
import com.ufabc.moduloconteudo.R
import kotlinx.android.synthetic.main.fragment_configuration.view.*


class ConfigurationFragment : Fragment(){

    companion object {
        fun newInstance() = ConfigurationFragment()
    }

    private lateinit var viewModel: ConfigurationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view : View = inflater.inflate(R.layout.fragment_configuration, container, false)


        // This sets the default boldness of a view in onCreate time
        ConfigurationSingleton.persistConfigModificationsOnAllViews(view, context)


        ConfigurationSingleton.setSwitchPositioning(view)

        // Listener for bold text switch on runtime
        view.bold_switch.setOnCheckedChangeListener { btn, _ ->
            ConfigurationSingleton.changeBoldness(view, btn)
            ConfigurationSingleton.changeConfigExample(view.example_text)
        }

        // Listener for high contrast text switch
        view.high_contrast_switch.setOnCheckedChangeListener { btn, isChecked ->
            val message = "Alterar contraste"
            makeText(activity, message, Toast.LENGTH_SHORT).show()
        }

        view.vibrate_switch.setOnCheckedChangeListener {btn, is_checked ->
            ConfigurationSingleton.changeVibrateOpt(is_checked)

        }

        view.invalidate_ra_btn.setOnClickListener { _ ->
            ConfigurationSingleton.setRA("")
            startActivity(Intent(context, LoginActivity::class.java), null)
        }

        view.fab_btn_visibility.setOnCheckedChangeListener { btn, is_checked ->
            ConfigurationSingleton.changeFabVisibility(is_checked)
        }


        view.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                ConfigurationSingleton.bigText(view, i)
                ConfigurationSingleton.changeConfigExample(view.example_text)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
//                makeText(activity, "", Toast.LENGTH_SHORT).show()

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
//                makeText(activity, "", Toast.LENGTH_SHORT).show()

            }
        })


        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel::class.java)

        // TODO: Use ViewModel
    }
}
