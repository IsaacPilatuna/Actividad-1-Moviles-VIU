package com.example.actividad_1_moviles_viu

import android.content.Context
import androidx.fragment.app.FragmentActivity

class SharedPreferences {

    companion object{
        fun  getPreferenceString(activity: FragmentActivity, key:String):String?{
            val sharedPreferences =  activity.getSharedPreferences("AppSharedPreferences",
                Context.MODE_PRIVATE)
            return sharedPreferences.getString(key,null)
        }

        fun storePreferenceString(activity:FragmentActivity, value: String, key:String){
            val sharedPreferences =  activity.getSharedPreferences("AppSharedPreferences",
                Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.commit()
        }
    }
}