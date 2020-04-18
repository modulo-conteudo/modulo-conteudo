package com.ufabc.moduloconteudo.act_home.tabs.configuration


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_config.*
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
    private lateinit var v : View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view : View = inflater.inflate(R.layout.fragment_configuration, container, false)
        v = view

        // This sets the default boldness of a view in onCreate time
        ConfigurationSingleton.persistConfigModificationsOnAllViews(view, context)


//        ConfigurationSingleton.setSwitchPositioning(view)

        // Listener for bold text switch on runtime
        view.bold_switch.setOnClickListener { _ ->
            val intent = Intent(context, BoldTextActivity::class.java)
            startActivity(intent, null)
//            ConfigurationSingleton.persistConfigModificationsOnAllViews(view, null)

        }

        // Listener for high contrast text switch
        view.high_contrast_switch.setOnClickListener {
            val intent = Intent(context, HighContrastActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivityForResult(intent, 2)
        }

        view.vibrate_switch.setOnClickListener {_ ->
            val intent = Intent(context, VibrateActivity::class.java)
            startActivity(intent, null)
//            ConfigurationSingleton.persistConfigModificationsOnAllViews(view, null)
        }

        view.invalidate_ra_btn.setOnClickListener { _ ->
            ConfigurationSingleton.setRA("")
            startLoginActivity()

        }

        view.fab_btn_visibility.setOnClickListener { _ ->
            val intent = Intent(context, LibrasBtbActivity::class.java)
            startActivity(intent, null)
//            ConfigurationSingleton.persistConfigModificationsOnAllViews(view, null)
        }

        view.font_size_btn.setOnClickListener { _ ->
            val intent = Intent(context, FontSizeActivity::class.java)
            startActivityForResult(intent, 0)
//            ConfigurationSingleton.persistConfigModificationsOnAllViews(view, null)
        }

        view.color_selectors_btn.setOnClickListener { _ ->
            val intent = Intent(context, ColorSelectorActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivityForResult(intent, 2)
//            ConfigurationSingleton.persistConfigModificationsOnAllViews(view, null)

        }

        return view
    }

    private fun startLoginActivity() {
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent, null)
//        activity?.finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        ConfigurationSingleton.persistConfigModificationsOnAllViews(v, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel::class.java)

        // TODO: Use ViewModel
    }

}
