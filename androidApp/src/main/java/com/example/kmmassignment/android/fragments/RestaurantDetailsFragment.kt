package com.example.kmmassignment.android.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.kmmassignment.android.MainActivity
import com.example.kmmassignment.android.R
import com.example.kmmassignment.android.databinding.RestaurantDetailsFragmentBinding
import com.example.kmmassignment.android.other.Constants.DETAIL_FRAGMENT_TITLE

class RestaurantDetailsFragment : Fragment(R.layout.restaurant_details_fragment) {

    private val args by navArgs<RestaurantDetailsFragmentArgs>()

    private lateinit var binding: RestaurantDetailsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = RestaurantDetailsFragmentBinding.bind(view)

        val supportActionBar = (activity as MainActivity).supportActionBar
        supportActionBar?.apply {
            show()
            title = DETAIL_FRAGMENT_TITLE
        }

        binding.apply {
            val restaurantData = args.data

            Glide.with(this@RestaurantDetailsFragment)
                .load(restaurantData.logo)
                .into(restaurantDetailIv)

            restaurantNameTv.text = restaurantData.name
            addressTv.text = restaurantData.address
            phoneNoTv.text = restaurantData.phoneNumber
            descriptionTv.text = restaurantData.description
        }
    }
}