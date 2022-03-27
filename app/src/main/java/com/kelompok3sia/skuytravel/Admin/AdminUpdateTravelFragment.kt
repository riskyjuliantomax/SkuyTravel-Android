package com.kelompok3sia.skuytravel.Admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kelompok3sia.skuytravel.Model.Travel
import com.kelompok3sia.skuytravel.R
import com.kelompok3sia.skuytravel.viewModel.TravelViewModel
import kotlinx.android.synthetic.main.fragment_admin_update_travel.view.*

class AdminUpdateTravelFragment : Fragment() {
    private val args by navArgs<AdminUpdateTravelFragmentArgs>()
    private lateinit var mTravelViewModel: TravelViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_admin_update_travel, container, false)

        view.btn_back_tambahTravel.setOnClickListener {
            findNavController().navigate(R.id.action_adminUpdateTravelFragment_to_adminTravelListFragment)
        }

        mTravelViewModel = ViewModelProvider(this).get(TravelViewModel::class.java)


        view.txt_update_nama_travel.setText(args.curTravel.nama)
        view.txt_update_mobil_bm.setText(args.curTravel.bmMobil)
        view.txt_update_daerahAwal.setText(args.curTravel.daerahAwal)
        view.txt_update_daerahTujuan.setText(args.curTravel.daerahTujuan)
        view.txt_update_mobil_merek.setText(args.curTravel.merekMobilval)
        view.txt_update_noHp.setText(args.curTravel.nohp)
        val id = args.curTravel.id
        return view
    }
}