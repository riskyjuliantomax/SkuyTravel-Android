package com.kelompok3sia.skuytravel.Data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kelompok3sia.skuytravel.Model.Travel

@Dao
interface TravelDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun tambahTravel(travel: Travel)

    @Query("SELECT * FROM Travel_Data ORDER BY id ASC")
    fun bacaSemuData(): LiveData<List<Travel>>

    @Update
    suspend fun updateTravel(travel: Travel)

    @Delete
    suspend fun hapusTravel(travel: Travel)

    @Query("DELETE FROM Travel_Data")
    suspend fun hapusSemua()
}