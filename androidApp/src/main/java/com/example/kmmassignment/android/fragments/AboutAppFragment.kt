package com.example.kmmassignment.android.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kmmassignment.android.MainActivity
import com.example.kmmassignment.android.R
import com.example.kmmassignment.android.databinding.AboutAppFragmentBinding
import com.example.kmmassignment.android.other.Constants.ABOUT_FRAGMENT_TITLE

class AboutAppFragment : Fragment(R.layout.about_app_fragment) {

    private lateinit var binding: AboutAppFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = AboutAppFragmentBinding.bind(view)

        binding.aboutBottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> findNavController().navigate(R.id.restaurantListFragment)
            }
            true
        }

        val supportActionBar = (activity as MainActivity).supportActionBar
        supportActionBar?.apply {
            show()
            title = ABOUT_FRAGMENT_TITLE
        }
    }

    override fun onResume() {
        super.onResume()
        binding.aboutBottomNavView.selectedItemId = R.id.about
    }
}