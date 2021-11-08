package com.example.kmmassignment.android.di


import com.example.kmmassignment.android.network.RestaurantApi
import com.example.kmmassignment.android.other.Constants.RESTAURANT_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun createRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(RESTAURANT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun createRetrofitApi(retrofit: Retrofit): RestaurantApi =
        retrofit.create(RestaurantApi::class.java)
}