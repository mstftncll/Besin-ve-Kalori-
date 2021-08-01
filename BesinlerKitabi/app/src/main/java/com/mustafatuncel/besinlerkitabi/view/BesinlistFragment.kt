package com.mustafatuncel.besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafatuncel.besinlerkitabi.R
import com.mustafatuncel.besinlerkitabi.adapter.BesinRecyleradapter
import com.mustafatuncel.besinlerkitabi.viewmodel.BesinlistesiViewModel
import kotlinx.android.synthetic.main.fragment_besinlist.*

class BesinlistFragment : Fragment() {

    private lateinit var viewModel :BesinlistesiViewModel
    private val recyclerBesinAdapter =BesinRecyleradapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besinlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //view model initilaze bağlama
        viewModel=ViewModelProviders.of(this).get(BesinlistesiViewModel::class.java)
        viewModel.refreshData()
        BesinlistrecyclerView.layoutManager=LinearLayoutManager(context)
        BesinlistrecyclerView.adapter=recyclerBesinAdapter
        //sayfayı yenilerse olacaklar
        swiperefreshLayout.setOnRefreshListener {
            besinyukleniyorprogbar.visibility=View.VISIBLE
            besinhatamesaj.visibility=View.GONE
            BesinlistrecyclerView.visibility=View.GONE
            viewModel.refreshFromInternet()
            swiperefreshLayout.isRefreshing=false

        }

        observeLivedata()
    }
    //gozlemleyeceğimiz durumlara gore yapılması gereknler
    //gozlemleyip veri alma
    fun observeLivedata(){
    viewModel.besinler.observe(viewLifecycleOwner, Observer {besinler->
        besinler?.let {
         BesinlistrecyclerView.visibility=View.VISIBLE
         recyclerBesinAdapter.besinlistesiniguncelle(besinler)
        }
    })
        viewModel.besinhatamesaj.observe(viewLifecycleOwner, Observer { hata->
            hata?.let {
                if (it==true){
                    besinhatamesaj.visibility=View.VISIBLE
                    BesinlistrecyclerView.visibility=View.GONE
                }else
                {
                    besinhatamesaj.visibility=View.GONE//gosterme
                }
            }

        })
        viewModel.besinyukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor->
            yukleniyor?.let {
                if (it){
                    BesinlistrecyclerView.visibility=View.GONE
                    besinhatamesaj.visibility=View.GONE
                    besinyukleniyorprogbar.visibility=View.VISIBLE
                }else{
                    besinyukleniyorprogbar.visibility=View.GONE
                }
            }
        })
    }

}