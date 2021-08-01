package com.mustafatuncel.besinlerkitabi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity//sqlitw tablo olarak kaydolur artık 1 tane primary key olusturacagız
data class besin(
    @ColumnInfo(name="isim")//room  için sqlite
    @SerializedName("isim")//retrofit için seri hale getiriyoruz
    val besinisim :String?,
    @ColumnInfo(name="kalori")
    @SerializedName("kalori")
    val besinkalori :String?,
    @ColumnInfo(name="karbonhidrat")
    @SerializedName("karbonhidrat")
    val besinkarbonhidrat :String?,
    @ColumnInfo(name="protein")
    @SerializedName("protein")
    val besinprotein :String?,
    @ColumnInfo(name="yag")
    @SerializedName("yag")
    val besinyag :String?,
    @ColumnInfo(name="gorsel")
    @SerializedName("gorsel")
    val besingorel :String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}