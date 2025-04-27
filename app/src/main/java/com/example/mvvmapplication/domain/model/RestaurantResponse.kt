package com.example.mvvmapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class RestaurantResponse(

    @SerialName("results") var results: List<Results> = mutableListOf<Results>(),
    @SerialName("message") var message: String

) : Parcelable

@Parcelize
data class Results(

    @SerialName("restaurantId") var restaurantId: Int,
    @SerialName("name") var name: String,
    @SerialName("Location") var Location: Location,
    @SerialName("imagePath") var imagePath: String,
    @SerialName("website") var website: String,
    @SerialName("phoneNumber") var phoneNumber: String

) : Parcelable

@Parcelize
data class Location(

    @SerialName("lat") var lat: String,
    @SerialName("lon") var lon: String

) : Parcelable