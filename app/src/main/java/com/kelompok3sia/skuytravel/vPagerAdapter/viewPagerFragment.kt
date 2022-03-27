package com.kelompok3sia.skuytravel.vPagerAdapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kelompok3sia.skuytravel.R
import com.kelompok3sia.skuytravel.SplashScreen.SplashScreen1Fragment
import com.kelompok3sia.skuytravel.SplashScreen.SplashScreen2Fragment
import com.kelompok3sia.skuytravel.SplashScreen.SplashScreen3Fragment
import com.kelompok3sia.skuytravel.databinding.FragmentViewPagerBinding

class viewPagerFragment : Fragment() {

    lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):
            View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_view_pager, container, false)
        binding = FragmentViewPagerBinding.bind(view)
        val fragmentList = arrayListOf(
            SplashScreen1Fragment(),
            SplashScreen2Fragment(),
            SplashScreen3Fragment()
        )
        val adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager,lifecycle)

        binding.splashScreen.adapter = adapter
        return view
    }

}