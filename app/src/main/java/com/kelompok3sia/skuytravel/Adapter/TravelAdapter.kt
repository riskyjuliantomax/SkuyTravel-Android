package com.kelompok3sia.skuytravel.Adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kelompok3sia.skuytravel.Admin.AdminMainActivity
import com.kelompok3sia.skuytravel.Admin.AdminTravelListFragmentDirections
import com.kelompok3sia.skuytravel.Model.Travel
import com.kelompok3sia.skuytravel.R
import com.kelompok3sia.skuytravel.User.MainActivity
import com.kelompok3sia.skuytravel.User.ProfileActivity
import kotlinx.android.synthetic.main.fragment_admin_list_custom_row.view.*

class TravelAdapter : RecyclerView.Adapter<TravelAdapter.MyViewHolder>(){
    private lateinit var database: DatabaseReference
    private var travelList = emptyList<Travel>()

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelAdapter.MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(com.kelompok3sia.skuytravel.R.layout.fragment_admin_list_custom_row, parent,false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val curItem = travelList[position]
        holder.itemView.tv_cl_list_namaTravel.text =curItem.nama
        holder.itemView.tv_tujuan.text = curItem.daerahAwal
        holder.itemView.tv_tujuan2.text = curItem.daerahTujuan
        holder.itemView.tv_tharga.text = curItem.tarifHarga
        holder.itemView.tv_nohp.text = curItem.nohp

        holder.itemView.cl_customrow_list.setOnClickListener {
            val aksi = AdminTravelListFragmentDirections.actionAdminTravelListFragment2ToAdminUpdateTravelFragment(curItem)
            holder.itemView.findNavController().navigate(aksi)
        }

    }

    fun tampilkanData(travel: List<Travel>){
        this.travelList = travel
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return travelList.size
    }


}