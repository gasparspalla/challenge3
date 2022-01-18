package com.example.earthquakemonitor.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.earthquakemonitor.Eartquake
import com.example.earthquakemonitor.api.ApiResponseStatus
import com.example.earthquakemonitor.database.EqDataBase
import com.example.earthquakemonitor.database.MainRepository
import com.example.earthquakemonitor.database.getDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

private val TAG=MainViewModel::class.java.simpleName
class MainViewModel(application: Application): AndroidViewModel(application) {

    private val database= getDataBase(application)
    private val repository=MainRepository(database)

    val eqList=repository._eqList

    private val _status= MutableLiveData<ApiResponseStatus>()

    val status:LiveData<ApiResponseStatus>
    get()=_status

    init {
        viewModelScope.launch {
            try {
                _status.value=ApiResponseStatus.LOADING
                repository.fetchEartquake()
                _status.value=ApiResponseStatus.DONE

            }
            catch (e:UnknownHostException){
                Log.d(TAG,"No internet connection",e)
                _status.value=ApiResponseStatus.ERROR

            }

        }
    }
    
}