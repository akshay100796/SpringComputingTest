package com.example.springtak.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager(private val context: Context) {
    @Volatile
    var sharePref: SharedPreferences? = null



    companion object INSTANCE {
        fun getInstance(context: Context) : SharedPreferences? = context.getSharedPreferences("SSP", Context.MODE_PRIVATE)
        fun getObjectInstance(context: Context) : SharedPreferences? = context.getSharedPreferences("SSPO",Context.MODE_PRIVATE)
    }


    private fun initPrefIfNull() {
        synchronized(context) {
            if (sharePref == null) {
                sharePref = context.getSharedPreferences("SSP", Context.MODE_PRIVATE)
                sharePref?.edit()?.apply()
            }
        }
    }

    //String data
    fun saveStringData(key: String, data: String?) {
        initPrefIfNull()
        sharePref?.edit()?.putString(key, data)?.apply() }
    fun getStringData(key: String): String? {
        initPrefIfNull()
        return sharePref?.getString(key, null)
    }




}