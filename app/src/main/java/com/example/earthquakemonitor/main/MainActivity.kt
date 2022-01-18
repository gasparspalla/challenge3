package com.example.earthquakemonitor.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquakemonitor.Eartquake
import com.example.earthquakemonitor.api.ApiResponseStatus
import com.example.earthquakemonitor.databinding.ActivityMainBinding
import com.example.earthquakemonitor.databinding.EqListItemBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding_list:EqListItemBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding_list=EqListItemBinding.inflate(layoutInflater)

        binding.eqRecycler.layoutManager=LinearLayoutManager(this)

        viewModel=ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)

        val adapter= EqAdapter(this)
        binding.eqRecycler.adapter=adapter

        viewModel.eqList.observe(this, Observer {
            adapter.submitList(it)
            handleEmptyView(it)

        })

        viewModel.status.observe(this){
            when{
                (it==ApiResponseStatus.LOADING)-> {
                    binding.progressBar.visibility=View.VISIBLE
                }
                (it==ApiResponseStatus.DONE)->{
                    binding.progressBar.visibility=View.GONE

                }
                (it==ApiResponseStatus.ERROR)->{
                    binding.progressBar.visibility=View.GONE

                }

            }
        }

        adapter.onItemClickListener={
            startActivity(it)

        }

    }

    private fun startActivity(eartquake: Eartquake) {
            val intent=Intent(this,DetailMainActivity::class.java)
            intent.putExtra(DetailMainActivity.EARTHQUAKE_VALUE_KEY,eartquake)
            startActivity(intent)

    }

    private fun handleEmptyView(eqList:MutableList<Eartquake>) {
        if (eqList.isEmpty()) binding.eqEmptyView.visibility = View.VISIBLE
        else binding.eqEmptyView.visibility = View.GONE
    }

}

