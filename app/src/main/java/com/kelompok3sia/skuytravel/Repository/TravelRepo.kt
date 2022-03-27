package com.kelompok3sia.skuytravel.Repository

import androidx.lifecycle.LiveData
import com.kelompok3sia.skuytravel.Data.TravelDAO
import com.kelompok3sia.skuytravel.Model.Travel

class TravelRepo(private val travelDAO: TravelDAO) {
    val bacaSemuaData : LiveData<List<Travel>> = travelDAO.bacaSemuData()

    suspend fun tambahTravel(travel: Travel){
        travelDAO.tambahTravel(travel)
    }

    suspend fun updateTravel(travel: Travel){
        travelDAO.updateTravel(travel)
    }
    suspend fun hapusTravel(travel: Travel){
        travelDAO.hapusTravel(travel)
    }
    suspend fun hapusSemuaTravel(){
        travelDAO.hapusSemua()
    }
}