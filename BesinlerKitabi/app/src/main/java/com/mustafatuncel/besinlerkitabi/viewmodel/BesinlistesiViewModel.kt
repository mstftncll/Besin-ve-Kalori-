package com.mustafatuncel.besinlerkitabi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mustafatuncel.besinlerkitabi.model.besin
import com.mustafatuncel.besinlerkitabi.servis.BesinAPIService
import com.mustafatuncel.besinlerkitabi.servis.BesinDatabase
import com.mustafatuncel.besinlerkitabi.util.OzelSharedPrefences
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class BesinlistesiViewModel(application: Application):BaseViewModel(application) {
val besinler=MutableLiveData<List<besin>>()//cekilen besinler
val besinhatamesaj=MutableLiveData<Boolean>()
val besinyukleniyor=MutableLiveData<Boolean>()
    private  var guncellemezamani=10*60*1000*1000*1000L//nanotime cevirme

    private  val besinapiservis=BesinAPIService()//servisimizi kullanmak için nesne olusturuacagız
    private val disposable =CompositeDisposable()//kullan at on bellek
    private val ozelSharedPrefences=OzelSharedPrefences(getApplication())

    fun refreshData(){
     val kaydedilmezamani=ozelSharedPrefences.zamaniAl()
     //10 dk fazla ise internetten al
        if (kaydedilmezamani !=null && kaydedilmezamani !=0L && System.nanoTime()-kaydedilmezamani<guncellemezamani)
        {
            //sqlite dan cek roomdan al
            verileriSqlitedanal()
        }else
        {
            verileriinternettenal()
        }
    }
    fun refreshFromInternet(){
        verileriinternettenal()
    }

    private  fun verileriSqlitedanal(){
        besinyukleniyor.value=true
        launch {
           val besinlistesi=BesinDatabase(getApplication()).besindao().getAllbesin()
            besinlerigoster(besinlistesi)
            Toast.makeText(getApplication(),"besinleri Roomdan aldık",Toast.LENGTH_LONG).show()
        }
    }

  private   fun verileriinternettenal()
    {
    besinyukleniyor.value=true
        //ıo thread,default,uı
     disposable.add(
         besinapiservis.getData()
             .subscribeOn(Schedulers.newThread())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeWith(object :DisposableSingleObserver<List<besin>>(){
                 override fun onSuccess(t: List<besin>) {
                    //basarılı olursa
                     //life data
                    sqlitesakla(t)
                     Toast.makeText(getApplication(),"besinleri İnternetten aldık",Toast.LENGTH_LONG).show()
                 }

                 override fun onError(e: Throwable) {
                     //hata alırsak
                     besinhatamesaj.value=true
                     besinyukleniyor.value=false
                     e.printStackTrace()
                 }

             })

     )
    }
    private fun besinlerigoster(besinlerListeleri :List<besin>){
        besinler.value=besinlerListeleri
        besinhatamesaj.value=false
        besinyukleniyor.value=false
    }
    //veri kaydetme
    private fun sqlitesakla(besinListesi:List<besin>){
        //dao yu direk çaıramayız corutine ile ulasıyoruz
        //arka plan çalıştırma
        launch {
           //dao ya ulaşmak için
            val dao=BesinDatabase(getApplication()).besindao()
            dao.deleteallbesin()
            val uuidListesi=dao.insertAll(*besinListesi.toTypedArray())//tek tek dizi oalrak verecek
            var i=0
            while (i<besinListesi.size){
                besinListesi[i].uuid=uuidListesi[i].toInt()
                i=i+1
            }
            besinlerigoster(besinListesi)
        }
        ozelSharedPrefences.zamaniKaydet(System.nanoTime())
    }
}
//lifecycle
//mutablelivedata degiştirilerbilir
//veri varsa goster yoksa hata ver yuklenyorsa prosserbar goster