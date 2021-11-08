package com.example.kmmassignment.android.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantData(
    val address: String,
    val description: String,
    val id: Int,
    val logo: String,
    val name: String,
    @SerializedName("phone_number") val phoneNumber: String,
    val review: String,
    val type: String,
    val uid: String
): Parcelable