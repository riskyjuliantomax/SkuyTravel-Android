package com.kelompok3sia.skuytravel.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kelompok3sia.skuytravel.Admin.AdminMainActivity
import com.kelompok3sia.skuytravel.LoginActivity
import com.kelompok3sia.skuytravel.R
import com.kelompok3sia.skuytravel.databinding.ActivityEditProfileBinding
import com.kelompok3sia.skuytravel.databinding.ActivityProfileBinding

class EditProfileActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private var _binding: ActivityEditProfileBinding?=null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditProfileBinding.inflate(layoutInflater)
        mAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)

        binding.btnKembaliMain.setOnClickListener{
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        binding.btnUpdate.setOnClickListener {

            val nama = binding.txtUserEditNama.text.toString()
            val nohp = binding.txtUserEditNohp.text.toString()
            val alamat = binding.txtUserEditAlamat.text.toString()
            val bio = binding.txtUserEditBio.text.toString()
            updateData(nama , nohp , alamat , bio)
        }
    }
    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        val userUid = mAuth.uid
        updateUI(currentUser)
        updateForm("${userUid}")
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

    private  fun  updateForm(userUid: String){
        database = FirebaseDatabase.getInstance().getReference("USERS")
        database.child(userUid).get().addOnSuccessListener {
            if (it.exists()) {
                val name = it.child("nama").value
                binding.txtUserEditNama.setText("${name}")

                val nohp = it.child("nohp").value
                binding.txtUserEditNohp.setText("${nohp}")

                val alamat = it.child("alamat").value
                binding.txtUserEditAlamat.setText("${alamat}")

                val bio = it.child("bio").value
                binding.txtUserEditBio.setText("${bio}")

            }
        }
    }

    private fun updateData(nama:String , nohp:String, alamat:String , bio:String){

        database = FirebaseDatabase.getInstance().getReference("USERS")
        val user = mapOf<String, String>(
            "nama" to nama,
            "nohp" to nohp,
            "alamat" to alamat,
            "bio" to bio
        )
        val userUid = mAuth.uid
        database.child("${userUid}").updateChildren(user).addOnSuccessListener {
            Toast.makeText(this,"Berhasil Ubah",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,ProfileActivity::class.java))
        }.addOnFailureListener{
            Toast.makeText(this,"Gagal Ubah",Toast.LENGTH_SHORT).show()
        }
    }
}