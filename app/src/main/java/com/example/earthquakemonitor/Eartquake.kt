package com.example.earthquakemonitor

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "eartquakes")
data class Eartquake(@PrimaryKey val id:String, val magnitude:Double, val place:String, val time:Long,
                     val longitude:Double, val latitude:Double):Parcelable