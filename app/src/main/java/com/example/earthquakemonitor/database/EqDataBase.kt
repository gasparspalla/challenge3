package com.example.earthquakemonitor.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.earthquakemonitor.Eartquake

@Database(entities = [Eartquake::class], version = 1)
abstract class EqDataBase :RoomDatabase(){

    abstract val eqDao:EqDAO

}
private lateinit var INSTANCE:EqDataBase

fun getDataBase(context: Context):EqDataBase{
    synchronized(EqDataBase::class.java){
        if (!::INSTANCE.isInitialized){
            INSTANCE= Room.databaseBuilder(
                context.applicationContext,
                EqDataBase::class.java,
                "eartquake_DB"
            ).build()
        }
        return INSTANCE
    }
}
