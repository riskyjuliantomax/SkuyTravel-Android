package com.kelompok3sia.skuytravel.SplashScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.kelompok3sia.skuytravel.R
import com.kelompok3sia.skuytravel.databinding.FragmentSplashScreen1Binding


class SplashScreen1Fragment : Fragment() {

    private lateinit var binding : FragmentSplashScreen1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_splash_screen1, container, false)
        binding = FragmentSplashScreen1Binding.bind(view)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.splashScreen)
        binding.txtselanjutnya.setOnClickListener{
            viewPager?.currentItem = 1
        }
        return view
    }
}