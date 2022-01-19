package com.example.earthquakemonitor.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakemonitor.Eartquake
import com.example.earthquakemonitor.R
import com.example.earthquakemonitor.databinding.EqListItemBinding

class EqAdapter(private val context: Context):ListAdapter<Eartquake, EqAdapter.EqViewHolder>(DiffCallback){

    companion object DiffCallback:DiffUtil.ItemCallback<Eartquake>(){
        override fun areItemsTheSame(oldItem: Eartquake, newItem: Eartquake): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Eartquake, newItem: Eartquake): Boolean {
            return oldItem==newItem
        }

    }

    lateinit var onItemClickListener: (Eartquake) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EqViewHolder {
        val binding=EqListItemBinding.inflate(LayoutInflater.from(parent.context))
        return EqViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EqViewHolder, position: Int) {
        val eartquake: Eartquake =getItem(position)
        holder.bind(eartquake)

    }

    inner class EqViewHolder(private val binding: EqListItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(eartquake: Eartquake){
            Log.d("LONGITUDE",eartquake.longitude.toString())
            binding.eqMagnitude.text= context.getString(R.string.magnitude, eartquake.magnitude)
            binding.eqPlace.text=eartquake.place
            binding.root.setOnClickListener{
                if (::onItemClickListener.isInitialized){
                    onItemClickListener(eartquake)
                }
            }
        }
    }
}