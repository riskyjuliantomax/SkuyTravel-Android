package com.kelompok3sia.skuytravel.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kelompok3sia.skuytravel.Admin.AdminMainActivity
import com.kelompok3sia.skuytravel.LoginActivity
import com.kelompok3sia.skuytravel.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private var _binding: ActivityProfileBinding?=null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        mAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)


        binding.btnKembaliMain.setOnClickListener{
            val userUid = mAuth.uid
            database = FirebaseDatabase.getInstance().getReference("USERS")
            database.child("${userUid}").get().addOnSuccessListener {
                if (it.exists()) {
                    val levelUser = it.child("levelUser").value
                    //Check Level User Is Admin or not
                    if("${levelUser}" == "0"){
                        startActivity(Intent(this, MainActivity::class.java))
                    }else if ("${levelUser}" == "1"){
                        startActivity(Intent(this, AdminMainActivity::class.java))
                    }

                }
            }
        }

        binding.btnLogout.setOnClickListener {
            mAuth.signOut()
            updateUI(mAuth.currentUser)
        }
        binding.btnEditProfile.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }
        binding.btnAboutusLogout.setOnClickListener{
            startActivity(Intent(this, AboutUsActivity::class.java))
        }
    }
    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        val userUid = mAuth.uid
        updateProfil("${userUid}")
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

    private  fun  updateProfil(userUid: String){
        database = FirebaseDatabase.getInstance().getReference("USERS")
        database.child(userUid).get().addOnSuccessListener {
            if (it.exists()) {
                val name = it.child("nama").value
                binding.tvNamaUser.setText("${name}")
                val gmail = it.child("email").value
                binding.tvGmail.setText("${gmail}")
                val bio = it.child("bio").value
                binding.tvBio.setText("${bio}")

                val nohp = it.child("nohp").value
                binding.tvNohp.setText("${nohp}")

                if(nohp != ""){
                    binding.tvNohp.visibility = View.VISIBLE
                }

                val alamat = it.child("alamat").value
                if(alamat != ""){
                binding.tvAlamat.visibility = View.VISIBLE
                }
                binding.tvAlamat.setText("${alamat}")

            }
        }
    }
}