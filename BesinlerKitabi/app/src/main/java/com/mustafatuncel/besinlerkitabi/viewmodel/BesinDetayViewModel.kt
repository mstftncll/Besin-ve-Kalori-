package com.mustafatuncel.besinlerkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mustafatuncel.besinlerkitabi.model.besin
import com.mustafatuncel.besinlerkitabi.servis.BesinDatabase
import kotlinx.coroutines.launch

class BesinDetayViewModel(application: Application): BaseViewModel(application) {
    val besinlivedata= MutableLiveData<besin>()

    fun roomVerisinial(uuid:Int){
    // room sqlitekullanmak icin kullanılan kutuphane
      launch {
          //detaylara bakmak
          val dao=BesinDatabase(getApplication()).besindao()
          val besin=dao.getBesin(uuid)
          besinlivedata.value=besin
      }
    }
}