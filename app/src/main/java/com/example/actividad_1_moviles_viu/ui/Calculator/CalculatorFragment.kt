package com.example.actividad_1_moviles_viu.ui.Calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.actividad_1_moviles_viu.R
import com.example.actividad_1_moviles_viu.databinding.FragmentCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private var _view:View?=null
    private val viewReference get() = _view!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(CalculatorViewModel::class.java)

        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        _view = binding.root
        addButtonsListeners()
        return viewReference
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addButtonsListeners(){
        val buttonsIds:IntArray = intArrayOf(R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn0, R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivision)
        buttonsIds.forEach { btnId ->
            val btn = viewReference.findViewById<Button>(btnId)
            btn.setOnClickListener(addCharOnClick)
        }

        val btnClear = viewReference.findViewById<Button>(R.id.btnClear)
        btnClear.setOnClickListener(clear)

        val btnEquals = viewReference.findViewById<Button>(R.id.btnEquals)
        btnEquals.setOnClickListener(calculate)

    }

    private val clear:View.OnClickListener= View.OnClickListener{
        view ->
        val calculationHolder = viewReference.findViewById<TextView>(R.id.calculationHolder)
        calculationHolder.text = ""
    }

    private val addCharOnClick:View.OnClickListener= View.OnClickListener {
        view  ->
        val calculationHolder = viewReference.findViewById<TextView>(R.id.calculationHolder)
        calculationHolder.text = "${calculationHolder.text}${(view as Button).text}"

    }

    private val calculate:View.OnClickListener= View.OnClickListener {
            view  ->
        val calculationHolder = viewReference.findViewById<TextView>(R.id.calculationHolder)
        val expression = ExpressionBuilder(calculationHolder.text.toString()).build()
        try {
            val result = expression.evaluate()
            val longResult = result.toLong()
            calculationHolder.text = longResult.toString()
        }catch (error:Exception){
            calculationHolder.text =  getString(R.string.syntax_error);
        }

    }
}