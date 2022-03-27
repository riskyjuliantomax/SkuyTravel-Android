package com.kelompok3sia.skuytravel.SplashScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kelompok3sia.skuytravel.Admin.AdminMainActivity
import com.kelompok3sia.skuytravel.R
import com.kelompok3sia.skuytravel.User.MainActivity
import com.kelompok3sia.skuytravel.databinding.FragmentPraMainBinding

class LoadingScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            if(onBoardingFinished()){
                findNavController().navigate(
                    R.id.action_loadingScreenFragment_to_praMainFragment
                )
            }else {
                findNavController().navigate(
                    R.id.action_loadingScreenFragment_to_viewPagerFragment
                )
            }
        },2000)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading_screen, container, false)
    }
    private fun onBoardingFinished():Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Selesai", false)
    }


}