package com.kelompok3sia.skuytravel.SplashScreen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.kelompok3sia.skuytravel.R
import com.kelompok3sia.skuytravel.databinding.FragmentSplashScreen3Binding

class SplashScreen3Fragment : Fragment() {

    private lateinit var binding: FragmentSplashScreen3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_splash_screen3, container, false)
        binding = FragmentSplashScreen3Binding.bind(view)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.splashScreen)

        binding.txtselanjutnya3.setOnClickListener{
            findNavController().navigate(
                R.id.action_viewPagerFragment_to_praMainFragment)
            onBoardingFinished()
        }
        return view
    }
    private fun onBoardingFinished(){
        val sharedpref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedpref.edit()
        editor.putBoolean("Selesai",true)
        editor.apply()
    }

}