package com.mustafatuncel.besinlerkitabi.servis

import com.mustafatuncel.besinlerkitabi.model.besin
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BesinAPIService {
    
    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    // retrofit url 2 ye bolmemızı ıstıyor 1 den fazla veri cekmede kolay olması ıcın
    //base_url ->
    private val BASE_URL="https://raw.githubusercontent.com/"
    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())//gson formatını modele cevir
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//rx java kullandıgımız icin retroolsa kullanmazdık
        .build()//
        .create(BesinAPI::class.java)//

    //erişim için fonksiton
    fun getData():Single<List<besin>>{
        return  api.getBesin()
    }
}