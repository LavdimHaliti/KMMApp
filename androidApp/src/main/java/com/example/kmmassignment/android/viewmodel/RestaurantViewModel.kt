package com.example.kmmassignment.android.viewmodel

import androidx.lifecycle.*
import com.example.kmmassignment.android.data.RestaurantData
import com.example.kmmassignment.android.network.RestaurantApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val restaurantApi: RestaurantApi
) : ViewModel() {

    private val _restaurantLiveData = MutableLiveData<List<RestaurantData>>()
    val restaurantLiveData: LiveData<List<RestaurantData>> get() = _restaurantLiveData

    init {
        viewModelScope.launch {
            val restaurant = restaurantApi.getRestaurantDataList()
            _restaurantLiveData.value = restaurant
        }
    }
}