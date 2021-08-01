package com.mustafatuncel.besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mustafatuncel.besinlerkitabi.R
import com.mustafatuncel.besinlerkitabi.model.besin
import com.mustafatuncel.besinlerkitabi.util.gorselIndir
import com.mustafatuncel.besinlerkitabi.util.placeholderyap
import com.mustafatuncel.besinlerkitabi.viewmodel.BesinDetayViewModel
import kotlinx.android.synthetic.main.fragment_besindetay.*


class BesindetayFragment : Fragment() {
    private lateinit var viewModel :BesinDetayViewModel
    private  var besinid=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besindetay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //gonderilen veri buraya alma geldimi //bundele bohca
        arguments?.let {
            besinid=BesindetayFragmentArgs.fromBundle(it).besinId

        }

        viewModel=ViewModelProviders.of(this).get(BesinDetayViewModel::class.java)
        viewModel.roomVerisinial(besinid)



        observeliveData()
    }
    fun observeliveData(){

        viewModel.besinlivedata.observe(viewLifecycleOwner,){besin->
            besin?.let {
                besinismi.text=it.besinisim
                besinkalori.text=it.besinkalori
                besinkarbonhidrat.text=it.besinkarbonhidrat
                besinprotein.text=it.besinprotein
                besinyag.text=it.besinyag
                context?.let {
                    besingorseli.gorselIndir(besin.besingorel, placeholderyap(it))
                }

            }
        }
    }


}