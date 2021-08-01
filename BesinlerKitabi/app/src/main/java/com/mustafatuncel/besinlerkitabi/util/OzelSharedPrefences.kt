package com.mustafatuncel.besinlerkitabi.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import androidx.preference.Preference
//veri çekmek için zaman sakladaık 5 dk tut sonra veri tekrar indir
class OzelSharedPrefences {
    companion object{
        private val ZAMAN="zaman"
        private  var sharedPrefences:SharedPreferences?=null
        private val lock=Any()
        @Volatile private var instance:OzelSharedPrefences?=null
        operator fun invoke(context: Context):OzelSharedPrefences= instance?: synchronized(lock){
            instance?: ozelSharedPrefencesYap(context).also {
                instance=it
            }
        }
        private fun ozelSharedPrefencesYap(context: Context):OzelSharedPrefences{
            sharedPrefences=androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return OzelSharedPrefences()
        }

    }
    fun zamaniKaydet(zaman:Long){
        sharedPrefences?.edit(commit =true){
            putLong(ZAMAN,zaman)
        }
    }
    fun zamaniAl()= sharedPrefences?.getLong(ZAMAN,0)


}