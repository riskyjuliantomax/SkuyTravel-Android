package com.kelompok3sia.skuytravel.Admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kelompok3sia.skuytravel.Model.Travel
import com.kelompok3sia.skuytravel.R
import com.kelompok3sia.skuytravel.viewModel.TravelViewModel
import kotlinx.android.synthetic.main.fragment_admin_tambah_travel.*
import kotlinx.android.synthetic.main.fragment_admin_tambah_travel.view.*

class AdminTambahTravelFragment : Fragment() {

    private lateinit var mTravelViewModel : TravelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_admin_tambah_travel, container, false)
        mTravelViewModel = ViewModelProvider(this).get(TravelViewModel::class.java)

        view.btn_tambah_travel.setOnClickListener {
            masukkandatabasesqllite()
        }
        view.btn_back_tambahTravel.setOnClickListener {
            findNavController().navigate(R.id.action_adminTambahTravelFragment2_to_adminTravelListFragment2)
        }
        return view
    }

    private fun masukkandatabasesqllite(){
        val namatravel = txt_nama_travel.text.toString()
        val nohp = txt_noHp.text.toString()
        val merekmobil = txt_mobil_merek.text.toString()
        val bmmobil = txt_mobil_bm.text.toString()
        val daerahawal = txt_daerahAwal.text.toString()
        val daerahtujuan = txt_daerahTujuan.text.toString()
        val tarif = txt_tarif.text.toString()

        if(namatravel != "" && nohp != "" && merekmobil != "" && bmmobil != "" && daerahawal != "" && daerahtujuan != "" && tarif != ""){

            val travel = Travel(0,namatravel,nohp,merekmobil,bmmobil,daerahawal,daerahtujuan,tarif)

            mTravelViewModel.tambahTravel(travel)

            Toast.makeText(requireContext(),"Data Berhasil ditambahkan", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_adminTambahTravelFragment2_to_adminTravelListFragment2)
        }else{
            Toast.makeText(requireContext(),"Silakan isi dulu datanya", Toast.LENGTH_SHORT).show()
        }
    }

}