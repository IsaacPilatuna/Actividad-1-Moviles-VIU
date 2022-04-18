package com.example.actividad_1_moviles_viu.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.actividad_1_moviles_viu.MainActivity
import com.example.actividad_1_moviles_viu.R
import com.example.actividad_1_moviles_viu.databinding.FragmentSettingsBinding
import java.util.*

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view: View = binding.root
        val languageGroup = view.findViewById<RadioGroup>(R.id.languageGroup);
        checkLanguage(view);
        languageGroup.setOnCheckedChangeListener{group, checkedId -> onLanguageChanged(group,checkedId)};
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onLanguageChanged(group:RadioGroup, checkedId:Int){
        if(checkedId == R.id.spanishButton){
            setLanguage(Languages.ES)
        }else{
            setLanguage(Languages.EN)
        }
    }

    private fun checkLanguage(view:View){
        val config = resources.configuration;
        val locale = config.locale;
        val language = locale.language;
        if(language == Languages.ES.value){
            val spanishButton = view.findViewById<RadioButton>(R.id.spanishButton);
            spanishButton.isChecked=true;
        }
    }

    private fun setLanguage(language:Languages ){
        val locale = Locale(language.value);
        val config = resources.configuration;
        val dm = resources.displayMetrics
        config.setLocale(locale);
        resources.updateConfiguration(config, dm);
        val refresh = Intent(activity, MainActivity::class.java);
        startActivity(refresh);
    }

    private enum class Languages(val value:String){
        EN("en"), ES("es")
    }
}