package com.example.earthquakemonitor.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.earthquakemonitor.Eartquake
import com.example.earthquakemonitor.R
import com.example.earthquakemonitor.databinding.ActivityDetailBinding
import java.text.SimpleDateFormat
import java.util.*

class DetailMainActivity:AppCompatActivity() {
    companion object {
        const val EARTHQUAKE_VALUE_KEY = "eartquake"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras!!
        val eartquake = extras.getParcelable<Eartquake>(EARTHQUAKE_VALUE_KEY)!!

        binding.magnitudeText.text=eartquake.magnitude.toString()
        binding.longitudeText.text=getString(R.string.get_longitude, eartquake.longitude)
        Log.d("LONGITUDE",eartquake.longitude.toString())
        binding.latitudeText.text=getString(R.string.get_latitude, eartquake.latitude)
        binding.placeText.text=eartquake.place

        val simpleDateFormat = SimpleDateFormat("dd/MMM/yyyy HH:mm:ss", Locale.getDefault())
        val date = Date(eartquake.time)
        val formattedString = simpleDateFormat.format(date)
        binding.timeText.text=formattedString

    }
}