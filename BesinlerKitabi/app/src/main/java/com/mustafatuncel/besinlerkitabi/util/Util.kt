package com.mustafatuncel.besinlerkitabi.util

import android.content.Context
import android.transition.CircularPropagation
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mustafatuncel.besinlerkitabi.R

/*
eklenti olarak hger resim de funk olarak çağırıyoruz
//eklenti resim için
fun String.benimEklentin(parametre:String){
    println(parametre)
}*/ // Glide kutuphanesi
fun ImageView.gorselIndir(url:String?,placeholder: CircularProgressDrawable){

    val options=RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
 Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}
//resim yulenirken prossbar donsun
fun placeholderyap(context:Context):CircularProgressDrawable{
    return  CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }
}