package com.kelompok3sia.skuytravel.User

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kelompok3sia.skuytravel.Adapter.TravelAdapter
import com.kelompok3sia.skuytravel.R
import com.kelompok3sia.skuytravel.viewModel.TravelViewModel
import kotlinx.android.synthetic.main.fragment_admin_travel_list.view.*
import kotlinx.android.synthetic.main.fragment_user_travel_list.view.*

class UserTravelListFragment : Fragment() {
    private lateinit var mTravelModelView: TravelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_user_travel_list, container, false)

        val adapter = TravelAdapter()
        val rv = view.user_travel_list_recycler_view
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        mTravelModelView = ViewModelProvider(this).get(TravelViewModel::class.java)
        mTravelModelView.bacaSemuaData.observe(viewLifecycleOwner, Observer {
                travel -> adapter.tampilkanData(travel)
        })

        return view
    }

}