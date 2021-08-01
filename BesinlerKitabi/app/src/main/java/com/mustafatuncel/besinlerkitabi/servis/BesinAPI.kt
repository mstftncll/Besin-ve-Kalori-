package com.mustafatuncel.besinlerkitabi.servis

import com.mustafatuncel.besinlerkitabi.model.besin
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
//call retrofit
//single rx java
interface BesinAPI {
    //Get, sunucudan veri cekmek
   // Post sunucuya veri yollamak
    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json
    // retrofit url 2 ye bolmemızı ıstıyor 1 den fazla veri cekmede kolay olması ıcın
    //base_url ->https://github.com/
    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getBesin() : Single<List<besin>>

}
