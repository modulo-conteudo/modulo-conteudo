package com.ufabc.moduloconteudo.act_home.tabs.configuration


import android.app.Activity
import android.app.Activity.RESULT_OK
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
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_config.BoldTextActivity
import com.ufabc.moduloconteudo.act_config.FontSizeActivity
import com.ufabc.moduloconteudo.act_config.LibrasBtbActivity
import com.ufabc.moduloconteudo.act_config.VibrateActivity
import com.ufabc.moduloconteudo.act_login.LoginActivity
import kotlinx.android.synthetic.main.fragment_configuration.view.*


class ConfigurationFragment : Fragment(){
    private val BOLD_ACTIVITY_REQUEST_CODE = 0
    private val HIGH_CONTRAST_ACTIVITY_REQUEST_CODE = 1
    private val VIBRATE__ACTIVITY_REQUEST_CODE = 2
    private val FAB_VI_ACTIVITY_REQUEST_CODE = 3

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


//        ConfigurationSingleton.setSwitchPositioning(view)

        // Listener for bold text switch on runtime
        view.bold_switch.setOnClickListener { _ ->
            val intent = Intent(context, BoldTextActivity::class.java)
            startActivity(intent, null)
        }

        // Listener for high contrast text switch
        view.high_contrast_switch.setOnClickListener { _->
            val message = "Alterar contraste"
            makeText(activity, message, Toast.LENGTH_SHORT).show()
        }

        view.vibrate_switch.setOnClickListener {_ ->
            val intent = Intent(context, VibrateActivity::class.java)
            startActivity(intent, null)
        }

        view.invalidate_ra_btn.setOnClickListener { _ ->
            ConfigurationSingleton.setRA("")
            startLoginActivity()
        }

        view.fab_btn_visibility.setOnClickListener { _ ->
            val intent = Intent(context, LibrasBtbActivity::class.java)
            startActivity(intent, null)
        }

        view.font_size_btn.setOnClickListener { _ ->
            val intent = Intent(context, FontSizeActivity::class.java)
            startActivity(intent, null)
        }

        return view
    }

    private fun startLoginActivity() {
        val intent = Intent(context, LoginActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent, null)
//        activity?.finish()
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel::class.java)

        // TODO: Use ViewModel
    }
}

//private fun Intent.getBooleanExtra(s: String): Boolean {
//    return s.toBoolean()
//}
