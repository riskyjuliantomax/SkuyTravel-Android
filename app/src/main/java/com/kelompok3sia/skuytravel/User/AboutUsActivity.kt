package com.kelompok3sia.skuytravel.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kelompok3sia.skuytravel.LoginActivity
import com.kelompok3sia.skuytravel.R
import com.kelompok3sia.skuytravel.databinding.ActivityAboutUsBinding
import com.kelompok3sia.skuytravel.databinding.ActivityMainBinding

class AboutUsActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private var _binding: ActivityAboutUsBinding?=null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        _binding = ActivityAboutUsBinding.inflate(layoutInflater)
        mAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)


        binding.btnKembaliMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnLokasiPerusahaan.setOnClickListener {
            startActivity(Intent(this, PerusahaanMapsActivity::class.java))
        }

        binding.btnLokasikampus.setOnClickListener {
            startActivity(Intent(this,PoliteknikCaltexRiauMapsActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        val userUid = mAuth.uid
        updateUI(currentUser)

    }
    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser == null){
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}