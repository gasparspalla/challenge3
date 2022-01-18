package com.example.earthquakemonitor.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.earthquakemonitor.Eartquake
import com.example.earthquakemonitor.api.EqJsonResponse
import com.example.earthquakemonitor.api.Geometry
import com.example.earthquakemonitor.api.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val dataBase: EqDataBase) {

    val _eqList:LiveData<MutableList<Eartquake>>
        get()=dataBase.eqDao.getEartquake()



    suspend fun fetchEartquake() {
        return withContext(Dispatchers.IO){
            val eqJsonResponse= service.getLastHourEartquake()
            Log.d("aa",eqJsonResponse.toString())
            val eqList=parseEqResult(eqJsonResponse)

            dataBase.eqDao.insertAll(eqList)

        }

    }

    private fun parseEqResult(eqJsonResponse: EqJsonResponse):MutableList<Eartquake> {
        val eqList= mutableListOf<Eartquake>()

        val featureList=eqJsonResponse.features

        for (feature in featureList ){
            val properties=feature.properties
            val id = feature.id
            val magnitude=properties.mag
            val place=properties.place
            val time=properties.time
            val geometry=feature.geometry
            val latitude=geometry.latitude
            val longitude=geometry.longitude
            eqList.add(Eartquake(id,magnitude,place,time,longitude,latitude))
        }
        return eqList
    }

}