package com.kelompok3sia.skuytravel.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kelompok3sia.skuytravel.Model.Travel


@Database(entities = [Travel::class], version = 1, exportSchema = false)
abstract class TravelDB : RoomDatabase() {
    abstract fun travelDAO(): TravelDAO

    companion object{
        @Volatile
        private var INSTANCE : TravelDB?=null

        fun getDb(context: Context):TravelDB{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TravelDB::class.java,
                    "Travel_Data"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}