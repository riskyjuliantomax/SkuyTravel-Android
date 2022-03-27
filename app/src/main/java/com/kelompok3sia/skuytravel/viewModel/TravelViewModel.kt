package com.kelompok3sia.skuytravel.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.kelompok3sia.skuytravel.Data.TravelDB
import com.kelompok3sia.skuytravel.Model.Travel
import com.kelompok3sia.skuytravel.Repository.TravelRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TravelViewModel(application: Application):AndroidViewModel(application) {

    val bacaSemuaData:LiveData<List<Travel>>

    private val repo:TravelRepo

    init {
        val travelDao = TravelDB.getDb(application).travelDAO()
        repo = TravelRepo(travelDao)
        bacaSemuaData = repo.bacaSemuaData
    }
    fun tambahTravel(travel: Travel){
        viewModelScope.launch(Dispatchers.IO) {
            repo.tambahTravel(travel)
        }
    }
    fun updateJadwal(travel: Travel){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateTravel(travel)
        }
    }
    fun hapusJadwal(travel: Travel){
        viewModelScope.launch(Dispatchers.IO) {
            repo.hapusTravel(travel)
        }
    }
    fun hapusSemuaJadwal(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.hapusSemuaTravel()
        }
    }
}