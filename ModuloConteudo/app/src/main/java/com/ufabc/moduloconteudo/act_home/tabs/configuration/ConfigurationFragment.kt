package com.ufabc.moduloconteudo.act_home.tabs.configuration


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ufabc.moduloconteudo.App
import com.ufabc.moduloconteudo.R
import com.ufabc.moduloconteudo.act_config.*
import com.ufabc.moduloconteudo.act_login.LoginActivity
import kotlinx.android.synthetic.main.fragment_configuration.view.*


class ConfigurationFragment : Fragment(){
    companion object {
        fun newInstance() = ConfigurationFragment()
    }

    private lateinit var viewModel: ConfigurationViewModel
    private lateinit var v : View

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        val view : View = inflater.inflate(R.layout.fragment_configuration, container, false)
        v = view
        ConfigurationSingleton.persistConfigModificationsOnAllViews(view, null)


        // Listener for bold text switch on runtime
        view.bold_switch.setOnClickListener { _ ->
            val intent = Intent(context, BoldTextActivity::class.java)
            startActivity(intent, null)

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
            startActivityForResult(intent, 0)
        }

        view.color_selectors_btn.setOnClickListener { _ ->
            if (AppPreferences.MyHighContrastStatus) {
                warn_hc_activated()
            } else {
                val intent = Intent(context, ColorSelectorActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivityForResult(intent, 2)
            }
        }

        return view
    }

    private fun warn_hc_activated() {
        MaterialAlertDialogBuilder(context)
            .setTitle("AVISO")
            .setMessage("Não é possível alterar as cores no modo de alto contraste")
            .setPositiveButton("OK"){_, _ ->}
            .setCancelable(false)
            .show()
    }


    override fun onResume() {
        super.onResume()
        if(ConfigurationSingleton.getRestartNeeded())
            callRestartAlertDialog()
    }

    private fun callRestartAlertDialog() {
        MaterialAlertDialogBuilder(context)
            .setTitle("AVISO")
            .setMessage("Reinicie o aplicativo para aplicar as configurações.")
            .setPositiveButton("OK"){_, _ ->
                ConfigurationSingleton.setRestartNeeded(false)
                activity?.finish()
            }
            .setCancelable(false)
            .show()
    }

    private fun startLoginActivity() {
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent, null)
        activity?.finish()
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
