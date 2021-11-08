package com.example.kmmassignment.android.network

import com.example.kmmassignment.android.data.RestaurantData
import retrofit2.http.GET

interface RestaurantApi {

    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurantDataList(): List<RestaurantData>
}