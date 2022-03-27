package com.kelompok3sia.skuytravel.Admin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.kelompok3sia.skuytravel.LoginActivity
import com.kelompok3sia.skuytravel.User.ProfileActivity
import com.kelompok3sia.skuytravel.databinding.ActivityAdminMainBinding
import com.kelompok3sia.skuytravel.viewModel.TravelViewModel


class AdminMainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private var _binding: ActivityAdminMainBinding?=null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var travelRecyclerView: RecyclerView
    private lateinit var mTravelViewModel: TravelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAdminMainBinding.inflate(layoutInflater)
        mAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)

//        sharedPreferences = getSharedPreferences("Test" , Context.MODE_PRIVATE)
        //Delete all Sharepreferences
//        sharedPreferences.edit().clear().commit()

        val toolbar : Toolbar = binding.toolbarAwal
        setSupportActionBar(toolbar)
        supportActionBar!!.title=""

        binding.btnMoreVert.setOnClickListener{
            startActivity(Intent(this, ProfileActivity::class.java))
        }


    }
    //View Pager Adapter
//    internal class ViewPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(
//        fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
//    ){
//        private val fragments: ArrayList<Fragment>
//        private val juduls: ArrayList<String>
//
//        init{
//            fragments = ArrayList()
//            juduls = ArrayList()
//        }
//
//        override fun getCount(): Int {
//            return fragments.size
//        }
//
//        override fun getItem(position: Int): Fragment {
//            return fragments[position]
//        }
//        fun tambahFragment(fragment: Fragment, judul: String) {
//            fragments.add(fragment)
//            juduls.add(judul)
//        }
//
//        override fun getPageTitle(position: Int): CharSequence? {
//            return juduls[position]
//        }
//    }



    //Start
    override fun onStart() {
        super.onStart()
        intent.setAction(null);
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
    private  fun  updateName(userUid: String){
        database = FirebaseDatabase.getInstance().getReference("USERS")
        database.child(userUid).get().addOnSuccessListener {
            if (it.exists()) {
                val name = it.child("nama").value
               binding.txPengguna.setText("${name}")

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}