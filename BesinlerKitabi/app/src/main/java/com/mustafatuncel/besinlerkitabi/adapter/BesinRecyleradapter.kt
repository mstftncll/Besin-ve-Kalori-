package com.mustafatuncel.besinlerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mustafatuncel.besinlerkitabi.R
import com.mustafatuncel.besinlerkitabi.databinding.BesinRecyclerRowBinding
import com.mustafatuncel.besinlerkitabi.model.besin
import com.mustafatuncel.besinlerkitabi.util.gorselIndir
import com.mustafatuncel.besinlerkitabi.util.placeholderyap
import com.mustafatuncel.besinlerkitabi.view.BesinlistFragmentDirections
import kotlinx.android.synthetic.main.besin_recycler_row.view.*

class BesinRecyleradapter(val besinlistesi:ArrayList<besin>):RecyclerView.Adapter<BesinRecyleradapter.BesinViewHolder>() {
    class BesinViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
//data bining view değiştiriyoruz
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
    val inflater=LayoutInflater.from(parent.context)
       val view=inflater.inflate(R.layout.besin_recycler_row,parent,false)
        //val view =DataBindingUtil.inflate<BesinRecyclerRowBinding>(inflater,R.layout.besin_recycler_row,parent,false)
        return BesinViewHolder(view)//recyler row ile diğeri birbirine bağlanıyor
    }
    override fun getItemCount(): Int {
        return besinlistesi.size
    }

    fun besinlistesiniguncelle( yenibesinlistesi: List<besin>){
        besinlistesi.clear()
        besinlistesi.addAll(yenibesinlistesi)
        notifyDataSetChanged()

    }
    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {

          holder.itemView.isim.text=besinlistesi.get(position).besinisim
          holder.itemView.kalori.text=besinlistesi.get(position).besinkalori



        holder.itemView.setOnClickListener {
            val action=BesinlistFragmentDirections.actionBesinlistFragmentToBesindetayFragment(besinlistesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
        //gorsel kısım eklenecek
        holder.itemView.imageview.gorselIndir(besinlistesi.get(position).besingorel, placeholderyap(holder.itemView.context))



   }



}