package com.kelompok3sia.skuytravel.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Travel_Data")
data class Travel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val nama:String,
    val nohp:String,
    val merekMobilval :String,
    val bmMobil:String,
    val daerahAwal:String,
    val daerahTujuan:String,
    val tarifHarga:String
):Parcelable
