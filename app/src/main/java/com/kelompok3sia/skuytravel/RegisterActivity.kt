package com.kelompok3sia.skuytravel

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kelompok3sia.skuytravel.Admin.AdminMainActivity
import com.kelompok3sia.skuytravel.FireBase.UserMC
import com.kelompok3sia.skuytravel.User.MainActivity
import com.kelompok3sia.skuytravel.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private var _binding: ActivityRegisterBinding?=null
    private val binding get() = _binding!!
    private lateinit var progressDialog : ProgressDialog
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setTitle(null)
        progressDialog.setMessage("Please Wait")

        binding.btnRegister.setOnClickListener{
            val tx_email =  binding.txtEmail.text.toString()
            val tx_nama = binding.txtNama.text.toString()
            val tx_password = binding.txtPass.text.toString()
            val tx_passConf = binding.txtConfirmpass.text.toString()

            if(tx_nama.isEmpty()){
                binding.txtNama.setError("Tidak Boleh Kosong !",null)
                binding.txtNama.setHintTextColor(Color.RED)
                binding.txtNama.requestFocus()
            }
            else if(tx_email.isEmpty()){
                binding.txtEmail.setError("Tidak Boleh Kosong !",null)
                binding.txtEmail.setHintTextColor(Color.RED)
                binding.txtEmail.requestFocus()

            }
            else if(tx_password.isEmpty()){
                binding.txtPass.setError("Tidak Boleh Kosong !", null)
                binding.txtPass.setHintTextColor(Color.RED)
                binding.txtPass.requestFocus()
            }
            else if(tx_passConf.isEmpty()){
                binding.txtConfirmpass.setError("Tidak Boleh Kosong !",null)
                binding.txtConfirmpass.setHintTextColor(Color.RED)
                binding.txtConfirmpass
            }
            else  if(tx_password != tx_passConf){
                binding.txtConfirmpass.setHintTextColor(Color.RED)
                binding.txtConfirmpass.setError("Password Tidak Sama", null)
            }
            else if (tx_password.length < 8){
                binding.txtPass.setError("Password Tidak Boleh <= 8 !", null)
                binding.txtPass.setHintTextColor(Color.RED)
                binding.txtPass.requestFocus()
            }
            else {
                progressDialog.show()
                // register user
                mAuth.createUserWithEmailAndPassword(tx_email,tx_password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = UserMC()
                        user.nama = tx_nama
                        user.email = tx_email
                        user.authid = mAuth.currentUser!!.uid
                        val dbRef = FirebaseDatabase.getInstance().reference.child("USERS")
                        val authid = mAuth.currentUser!!.uid
                        dbRef.child(authid).setValue(user).addOnCompleteListener {
                            if (it.isSuccessful) {
                                progressDialog.dismiss()
                                Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                                finish()
                                startActivity(Intent(this, MainActivity::class.java))
                            } else {
                                Toast.makeText(
                                    this,
                                    "Register Gagal Karena ${it.exception}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }else{
                        Toast.makeText(
                            this,
                            "Register Gagal Karena ${it.exception}",
                            Toast.LENGTH_SHORT
                        ).show()
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

                if("${levelUser}" == "0"){
                    finish()
                    startActivity(Intent(this, MainActivity::class.java))
                }else if("${levelUser}" == "1"){
                    finish()
                    startActivity(Intent(this, AdminMainActivity::class.java))
                }

            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}