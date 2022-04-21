package com.example.actividad_1_moviles_viu.ui.dialog

import android.R.attr
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.actividad_1_moviles_viu.R
import com.example.actividad_1_moviles_viu.SharedPreferences
import com.example.actividad_1_moviles_viu.ui.calculator.CalculatorFragment


class FragmentResultDialog : DialogFragment() {

    companion object {
        fun newInstance() = FragmentResultDialog()
    }

    private lateinit var holderValue:String;
    private lateinit var resultValue:String;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_result_dialog, container, false)
        loadValues(view)
        val btnShare = view.findViewById<Button>(R.id.btnShare)
        btnShare.setOnClickListener{share(screenShot(view))}
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun loadValues(view:View){
        holderValue = SharedPreferences.getPreferenceString(requireActivity(), CalculatorFragment.CALCULATION_HOLDER_KEY) ?: ""
        resultValue = SharedPreferences.getPreferenceString(requireActivity(), CalculatorFragment.RESULT_KEY) ?: ""
        val inputTxt = view.findViewById<TextView>(R.id.inputTxt)
        inputTxt.text = holderValue
        val resultTxt = view.findViewById<TextView>(R.id.resultTxt)
        resultTxt.text = resultValue

    }


    private fun share(bitmap: Bitmap){
        val shareString = getString(R.string.share_results)
        val pathOfBmp: String = MediaStore.Images.Media.insertImage(
            requireActivity().contentResolver,
            bitmap, shareString, null
        )
        val uri: Uri = Uri.parse(pathOfBmp)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareString)
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareString)
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        requireActivity().startActivity(Intent.createChooser(shareIntent, shareString))
    }

    private fun screenShot(view: View): Bitmap{
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

}