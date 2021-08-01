package com.mustafatuncel.besinlerkitabi.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mustafatuncel.besinlerkitabi.model.besin

@Database(entities = arrayOf(besin::class),version = 1)
abstract class BesinDatabase :RoomDatabase() {
    abstract fun besindao():BesinDAO
    //Singleton farklı tread lerde tek 1 objeye olassın
    companion object {

        @Volatile private  var instance :BesinDatabase?=null//instance ile ulasımları konrtrol edecegiz
       private  val lock=Any()
       operator  fun invoke(context: Context)= instance?: synchronized(lock)//invoke ateşlemek senkron şekilde yap
       {
           instance?: databaseolustur(context).also {
               instance=it
           }
       }

        private fun databaseolustur(context: Context) = Room.databaseBuilder(
            context.applicationContext, BesinDatabase::class.java, "besindatabase"
        ).build()
    }

}