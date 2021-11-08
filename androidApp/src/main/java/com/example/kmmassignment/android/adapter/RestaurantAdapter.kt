package com.example.kmmassignment.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kmmassignment.android.databinding.RestaurantListItemBinding
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.kmmassignment.android.data.RestaurantData

class RestaurantAdapter :
    ListAdapter<RestaurantData, RestaurantAdapter.RestaurantViewHolder>(RestaurantCompare()) {

    private var onItemClickListener: ((RestaurantData) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {

        val binding =
            RestaurantListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentRestaurant = getItem(position)

        if (currentRestaurant != null) {
            holder.bind(currentRestaurant)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(currentRestaurant) }
        }
    }

    fun setOnItemClickListener(listener: (RestaurantData) -> Unit) {
        onItemClickListener = listener
    }

    class RestaurantViewHolder(private val binding: RestaurantListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: RestaurantData) {

            Glide.with(itemView)
                .load(restaurant.logo)
                .into(binding.restaurantIv)

            binding.restaurantTv.text = restaurant.name
        }
    }

    class RestaurantCompare : DiffUtil.ItemCallback<RestaurantData>() {
        override fun areItemsTheSame(oldItem: RestaurantData, newItem: RestaurantData): Boolean {
            return newItem == oldItem
        }

        override fun areContentsTheSame(oldItem: RestaurantData, newItem: RestaurantData): Boolean {
            return newItem.name == oldItem.name
        }
    }
}