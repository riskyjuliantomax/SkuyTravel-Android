package com.kelompok3sia.skuytravel.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kelompok3sia.skuytravel.Admin.AdminMainActivity
import com.kelompok3sia.skuytravel.LoadingActivity
import com.kelompok3sia.skuytravel.LoginActivity
import com.kelompok3sia.skuytravel.RegisterActivity
import com.kelompok3sia.skuytravel.User.MainActivity
import com.kelompok3sia.skuytravel.databinding.FragmentPraMainBinding

class PraMainFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding : FragmentPraMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAuth = FirebaseAuth.getInstance()
        // Inflate the layout for this fragment
        binding = FragmentPraMainBinding.inflate(inflater,container,false)

        //Button Action Loign
        binding.btnLoginPra.setOnClickListener{
            activity.let {
                val intent = Intent(it, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        binding.btnRegisterPra.setOnClickListener {
            activity.let {
                val intent = Intent(it, RegisterActivity::class.java)
                startActivity(intent)
            }
        }

        return binding.root
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
                    requireActivity().run{
                        finish()
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }else if("${levelUser}" == "1"){
                    requireActivity().run {
                        finish()
                        startActivity(Intent(this, AdminMainActivity::class.java))
                    }
                }

            }
        }

    }

}