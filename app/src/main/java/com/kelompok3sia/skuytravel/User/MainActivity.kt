package com.kelompok3sia.skuytravel.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kelompok3sia.skuytravel.LoginActivity
import com.kelompok3sia.skuytravel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private var _binding: ActivityMainBinding?=null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        mAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)

//        binding.btnLogout.setOnClickListener{
//            mAuth.signOut()
//            updateUI(mAuth.currentUser)
//        }
       binding.welcomeCard.setOnClickListener{
           startActivity(Intent(this,ProfileActivity::class.java))
       }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        val userUid = mAuth.uid
        updateUI(currentUser)
        updateName("${userUid}")
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
    private  fun  updateName(userUid: String){
        database = FirebaseDatabase.getInstance().getReference("USERS")
        database.child(userUid).get().addOnSuccessListener {
            if (it.exists()) {
                val name = it.child("nama").value
                binding.nickuser.setText("${name}")

            }
        }
    }
}