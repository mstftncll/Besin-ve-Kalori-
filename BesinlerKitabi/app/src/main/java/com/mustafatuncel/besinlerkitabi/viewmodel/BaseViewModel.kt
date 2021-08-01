package com.mustafatuncel.besinlerkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

//corutine için uyg contexi için androdi view
//application istiyor
open class BaseViewModel(application: Application) : AndroidViewModel(application),CoroutineScope {
    private val job= Job()//corotine ne işe yaradıgını belirtiriz
    override val coroutineContext: CoroutineContext
        get() =job +Dispatchers.Main//arakda ne yaparsa yapsın maine donecek

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}