package com.kelompok3sia.skuytravel.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kelompok3sia.skuytravel.databinding.ActivityAdminMainBinding
import com.kelompok3sia.skuytravel.databinding.ActivityAdminTraveListBinding

class AdminTraveListActivity : AppCompatActivity() {
    private var _binding: ActivityAdminTraveListBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAdminTraveListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}