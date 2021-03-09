package com.britshbroadcast.flightready.view

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.britshbroadcast.flightready.R
import com.britshbroadcast.flightready.databinding.AdapterItemLayoutBinding

class FlightItemAdapter(private val itemList: List<String>): RecyclerView.Adapter<FlightItemAdapter.AdapterViewHolder>() {

    private var count: Int = itemList.size
    inner class AdapterViewHolder(val binding: AdapterItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            var isChecked = false
            binding.flightItemCardView.setOnClickListener {
                isChecked = if(!isChecked){
                    count--
                    if(count == 0){
                        Toast.makeText(it.context,"You are FlightReady!",Toast.LENGTH_SHORT).show()
                    }
                    it.setBackgroundColor(Color.parseColor("#4361EE"))
                    binding.checkedImageView.visibility = View.VISIBLE
                    !isChecked

                }else{
                    count++
                    it.setBackgroundColor(Color.parseColor("#F72585"))
                    binding.checkedImageView.visibility = View.INVISIBLE
                    !isChecked
                }
                Log.d("TAG_J", "$count")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val binding = AdapterItemLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
                return AdapterViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: AdapterViewHolder, position: Int) {

            with(viewHolder){
                with(itemList[position]){
                    when(this){
                        "headphones" -> binding.flightItemImageView.setImageResource(R.drawable.ic_headphones)
                        "book" ->  binding.flightItemImageView.setImageResource(R.drawable.ic_book)
                        "food" -> binding.flightItemImageView.setImageResource(R.drawable.ic_food)
                        "game" -> binding.flightItemImageView.setImageResource(R.drawable.ic_game)
                    }
                }
            }
    }

    override fun getItemCount(): Int = itemList.size
}