package com.example.earthquakemonitor.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.earthquakemonitor.Eartquake

@Dao
interface EqDAO{

    @Insert(onConflict = REPLACE)
    fun insertAll(eqList:MutableList<Eartquake>)

    @Query("SELECT * FROM eartquakes")
    fun getEartquake():LiveData<MutableList<Eartquake>>
}