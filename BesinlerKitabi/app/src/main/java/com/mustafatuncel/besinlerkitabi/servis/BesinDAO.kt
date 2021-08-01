package com.mustafatuncel.besinlerkitabi.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mustafatuncel.besinlerkitabi.model.besin

//indirlen verileri sqlite room olarak ekleme ön bellek oluşturma
@Dao
interface BesinDAO {
    //DATA accsess object
    @Insert
    suspend fun insertAll(vararg  besin: besin) : List<Long>
    //insert ->room ,insert into
    //suspend->corotunıe scoğe
    //vararg -- besin grupları cekme
    //List Long kong ıd
    @Query("SELECT * FROM besin")//veri çekme
    suspend fun  getAllbesin() : List<besin>

    @Query("SELECT *FROM besin WHERE uuid= :besinid")
    suspend fun getBesin(besinid:Int):besin

    @Query("DELETE FROM besin")
    suspend fun deleteallbesin()
}