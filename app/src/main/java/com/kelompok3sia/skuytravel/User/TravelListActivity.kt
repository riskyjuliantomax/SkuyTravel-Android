package com.kelompok3sia.skuytravel.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.kelompok3sia.skuytravel.LoginActivity
import com.kelompok3sia.skuytravel.R
import com.kelompok3sia.skuytravel.databinding.ActivityTravelListBinding

class TravelListActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private var _binding: ActivityTravelListBinding?=null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_list)
        _binding = ActivityTravelListBinding.inflate(layoutInflater)
        mAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)


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