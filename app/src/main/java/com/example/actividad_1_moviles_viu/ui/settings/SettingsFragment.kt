package com.example.actividad_1_moviles_viu.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.actividad_1_moviles_viu.Languages
import com.example.actividad_1_moviles_viu.MainActivity
import com.example.actividad_1_moviles_viu.R
import com.example.actividad_1_moviles_viu.SharedPreferences
import com.example.actividad_1_moviles_viu.databinding.FragmentSettingsBinding
import java.util.*


class SettingsFragment : Fragment() {
    companion object{
        const val LANGUAGE_KEY = "language"
    }
    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!


    private var _view:View? = null
    private val viewReference get() = _view!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        _view = binding.root
        val languageGroup = viewReference.findViewById<RadioGroup>(R.id.languageGroup)
        checkLanguage();
        languageGroup.setOnCheckedChangeListener{group, checkedId -> onLanguageChanged(group,checkedId)}
        return viewReference
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

    private fun checkLanguage(){

        var language = SharedPreferences.getPreferenceString(requireActivity(), LANGUAGE_KEY);
        val config = resources.configuration
        val locale = config.locale
        val restart = locale.language != language;
        if(language==null){
            language = locale.language
        }

        if(language == Languages.ES.value){
            val spanishButton = viewReference.findViewById<RadioButton>(R.id.spanishButton)
            spanishButton.isChecked=true
            setLanguage(Languages.ES, restart)
        }
    }

    private fun setLanguage(language: Languages, restart:Boolean=true){
        SharedPreferences.storePreferenceString(requireActivity(), language.value, LANGUAGE_KEY)
        val locale = Locale(language.value)
        val config = resources.configuration
        val dm = resources.displayMetrics
        config.setLocale(locale)
        resources.updateConfiguration(config, dm)
        if(restart){
            val refresh = Intent(activity, MainActivity::class.java)
            startActivity(refresh)
        }
    }


}