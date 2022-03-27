package com.kelompok3sia.skuytravel.Admin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kelompok3sia.skuytravel.R
import com.kelompok3sia.skuytravel.databinding.ActivityAdminInputMapsBinding
import kotlinx.android.synthetic.main.activity_admin_input_maps.*
import java.io.IOException

class AdminInputMapsActivity : AppCompatActivity(), OnMapReadyCallback ,
    GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityAdminInputMapsBinding
    internal var mGoogleMapApiClient: GoogleApiClient? = null
    internal lateinit var mLocationRequest: LocationRequest
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminInputMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("Test" , Context.MODE_PRIVATE)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.btnAmbil.setOnClickListener {
            AmbilLokasi()
        }
        binding.btnAmbil2.setOnClickListener{
            AmbilLokasi2()
        }
        binding.btnKembali.setOnClickListener{
            startActivity(Intent(this,AdminMainActivity::class.java))
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if(ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )== PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
            buildGoogleApiClient()
            mMap.isMyLocationEnabled= true
        }

    }
    protected fun buildGoogleApiClient(){
        mGoogleMapApiClient=GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API).build()
        mGoogleMapApiClient!!.connect()
    }
    override fun onConnected(bundle: Bundle?) {
        mLocationRequest = LocationRequest()
        mLocationRequest.interval = 1000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        if(ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )== PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this
                , android.Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.getFusedLocationProviderClient(this)
        }
    }
    fun CariLokasi(view: View){
        val lokasi: String = input_lokasi.text.toString()
        var listAlamat: List<Address>? = null
        if(lokasi == ""){
            Toast.makeText(this,"Masukkan Lokasi yang ingin dicari",
                Toast.LENGTH_SHORT).show()
        }else {
            val geocoder = Geocoder(this)
            try {
                listAlamat = geocoder.getFromLocationName(lokasi,1)
            }catch (e: IOException){
                e.printStackTrace()
            }
//            val test =sharedPreferences.getLong("daerah1_latitude",0)
            val alamat = listAlamat!![0]
            val latLng = LatLng(alamat.latitude,alamat.longitude)
            intent.putExtra("latitude", alamat.latitude.toDouble())
             intent.putExtra("longitude",alamat.longitude.toDouble())

            mMap.addMarker(MarkerOptions().position(latLng).title(lokasi))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f))
//            Toast.makeText(
//                this,
////                latLng.toString(),
//                alamat.latitude.toString()+" "+
//                        alamat.longitude.toString(),
//                Toast.LENGTH_SHORT
//            ).show()
            Toast.makeText(
                this,
//                latLng.toString(),
                alamat.latitude.toString()+" "+
                        alamat.longitude.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun AmbilLokasi(){
        //Deklarasi
        val edit = sharedPreferences.edit()
        var latitude_lokasi1 = intent.getDoubleExtra("latitude",0.0)
        var longitude_lokasi1 = intent.getDoubleExtra("longitude",0.0)

//        Toast.makeText(this,
//            latitude_lokasi1.toString()+" "+ longitude_lokasi1.toDouble(), Toast.LENGTH_LONG
//        ).show()

        if(latitude_lokasi1 != null || longitude_lokasi1 != null || longitude_lokasi1 != 0.0 ||latitude_lokasi1 != 0.0){
            //Memasuki Data ke Sharedpreferences
            edit.putFloat("daerah1_latitude", latitude_lokasi1.toFloat())
            edit.putFloat("daerah1_longitude",longitude_lokasi1.toFloat())
            edit.apply()
//            Log.d("Check Lokasi",latitude_lokasi1.toString()+" "+ longitude_lokasi.toString())
            Toast.makeText(
                this,"Berhasil Mengambil Lokasi 1 : "+
                latitude_lokasi1.toDouble()+" "+longitude_lokasi1.toDouble(),
                Toast.LENGTH_SHORT
            ).show()
        }else {
            Toast.makeText(
                this, "Masuki Lokasi Pertama",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun AmbilLokasi2(){
        //Deklarasi
        val edit = sharedPreferences.edit()
        val latitude_lokasi2 = intent.getDoubleExtra("latitude",0.0)
        val longitude_lokasi2 = intent.getDoubleExtra("longitude",0.0)

        if(latitude_lokasi2 != 0.0 && longitude_lokasi2 != 0.0){
            //Memasuki Data ke Sharedpreferences
            edit.putFloat("daerah2_latitude", latitude_lokasi2.toFloat())
            edit.putFloat("daerah2_longitude",longitude_lokasi2.toFloat())
            edit.apply()
//            Log.d("Check Lokasi",latitude_lokasi1.toString()+" "+ longitude_lokasi.toString())
            Toast.makeText(
                this,"Berhasil Mengambil Lokasi 2 : "+
                latitude_lokasi2.toDouble()+" "+longitude_lokasi2.toDouble(),
                Toast.LENGTH_SHORT
            ).show()
        }else {
            Toast.makeText(
                this, "Masuki Lokasi Kedua",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    override fun onConnectionSuspended(p0: Int){
    }
}