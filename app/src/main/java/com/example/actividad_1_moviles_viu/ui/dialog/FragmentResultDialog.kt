package com.example.actividad_1_moviles_viu.ui.dialog

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.actividad_1_moviles_viu.R
import com.example.actividad_1_moviles_viu.SharedPreferences
import com.example.actividad_1_moviles_viu.ui.calculator.CalculatorFragment

class FragmentResultDialog : DialogFragment() {

    companion object {
        fun newInstance() = FragmentResultDialog()
    }


    private lateinit var viewModel: FragmentResultDialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_result_dialog, container, false)
        loadValues(view)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentResultDialogViewModel::class.java)
    }

    private fun loadValues(view:View){
        val holderValue = SharedPreferences.getPreferenceString(requireActivity(), CalculatorFragment.CALCULATION_HOLDER_KEY)
        val resultValue = SharedPreferences.getPreferenceString(requireActivity(), CalculatorFragment.RESULT_KEY)
        val inputTxt = view.findViewById<TextView>(R.id.inputTxt)
        inputTxt.text = holderValue
        val resultTxt = view.findViewById<TextView>(R.id.resultTxt)
        resultTxt.text = resultValue


    }

}