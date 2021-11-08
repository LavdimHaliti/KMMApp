package com.example.kmmassignment.android.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kmmassignment.android.MainActivity
import com.example.kmmassignment.android.R
import com.example.kmmassignment.android.adapter.RestaurantAdapter
import com.example.kmmassignment.android.databinding.RestaurantListFragmentBinding
import com.example.kmmassignment.android.viewmodel.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantListFragment : Fragment(R.layout.restaurant_list_fragment) {

    private val viewModel: RestaurantViewModel by viewModels()
    private lateinit var restaurantAdapter: RestaurantAdapter

    private lateinit var binding: RestaurantListFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RestaurantListFragmentBinding.bind(view)

        restaurantAdapter = RestaurantAdapter()

        observeLivedata()

        restaurantAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("data", it)
            }
            findNavController().navigate(
                R.id.action_restaurantListFragment_to_restaurantDetailsFragment,
                bundle
            )
        }

        binding.restaurantRv.apply {
            adapter = restaurantAdapter
            layoutManager = LinearLayoutManager(this.context)
        }

        binding.apply {
            homeBottomNavView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.about -> findNavController().navigate(R.id.action_restaurantListFragment_to_aboutAppFragment)
                }
                true
            }

            homeBottomNavView.setOnLongClickListener {
                Toast.makeText(requireContext().applicationContext, "Home", Toast.LENGTH_SHORT)
                    .show()
                return@setOnLongClickListener true
            }
        }

        val supportActionBar = (activity as MainActivity).supportActionBar
        supportActionBar?.hide()
    }

    private fun observeLivedata() {
        viewModel.restaurantLiveData.observe(viewLifecycleOwner) { data ->
            if (data.isEmpty()) {
                restaurantAdapter.submitList(emptyList())
            } else {
                restaurantAdapter.submitList(data)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.homeBottomNavView.selectedItemId = R.id.home
    }
}
