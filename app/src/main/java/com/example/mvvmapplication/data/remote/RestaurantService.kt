package com.example.mvvmapplication.data.remote

import com.example.mvvmapplication.domain.model.RestaurantResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestaurantService {
    @GET("raw/kRMwpCJS")
    suspend fun getRestaurantList(): Response<RestaurantResponse>
}