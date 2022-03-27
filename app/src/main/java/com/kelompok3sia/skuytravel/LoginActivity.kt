package com.kelompok3sia.skuytravel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kelompok3sia.skuytravel.Admin.AdminMainActivity
import com.kelompok3sia.skuytravel.User.MainActivity
import com.kelompok3sia.skuytravel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var _binding: ActivityLoginBinding?=null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        //Proses Login
        binding.btnLogin.setOnClickListener{
            val tx_email = binding.txtEmail.text.toString()
            val tx_pass = binding.txtPassword.text.toString()

            if(tx_email.isEmpty()){
                binding.txtEmail.setError("Email Tidak Boleh Kosong",null)
            }else if (tx_pass.isEmpty()){
                binding.txtPassword.setError("Password Tidak Boleh Kosong",null)
            }else {
                mAuth.signInWithEmailAndPassword(tx_email,tx_pass).addOnCompleteListener{
                    if (it.isSuccessful){
                    auth("${mAuth.uid}")
                    } else {
                        Toast.makeText(this,"Email Dan Password Salah", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.uid
        updateUI("${currentUser}")
    }
    private fun updateUI(currentUser : String){
        database = FirebaseDatabase.getInstance().getReference("USERS")
        database.child(currentUser).get().addOnSuccessListener {
            if (it.exists()) {
                val levelUser = it.child("levelUser").value
                //Check Level User Is Admin or not
                if("${levelUser}" == "0"){
                    finish()
                    startActivity(Intent(this, MainActivity::class.java))
                }else if("${levelUser}" == "1"){
                    finish()
                    startActivity(Intent(this, AdminMainActivity::class.java))
                }else {
                    Toast.makeText(this,"Terjadi Error LU-001",Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun auth(uid : String){
        database = FirebaseDatabase.getInstance().getReference("USERS")
        database.child(uid).get().addOnSuccessListener {
            if(it.exists()){
                val levelUser = it.child("levelUser").value
                if("${levelUser}" == "0"){
                    startActivity(Intent(this, MainActivity::class.java))
                }else if("${levelUser}" == "1"){
                    startActivity(Intent(this, AdminMainActivity::class.java))
                }
            }else{
                Toast.makeText(this,"Email Dan Password Salah", Toast.LENGTH_SHORT).show()
            }
        }
    }

}