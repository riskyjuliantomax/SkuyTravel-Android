package com.kelompok3sia.skuytravel.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kelompok3sia.skuytravel.R
import com.kelompok3sia.skuytravel.databinding.ActivityPoliteknikCaltexRiauMapsBinding

class PoliteknikCaltexRiauMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityPoliteknikCaltexRiauMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPoliteknikCaltexRiauMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

        // Add a marker in Sydney and move the camera
        val lokasi = LatLng(0.5701695600966048, 101.42553500823699)
        mMap.addMarker(MarkerOptions().position(lokasi).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lokasi))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi,15F))
        mMap.uiSettings.isZoomControlsEnabled = true
    }
}