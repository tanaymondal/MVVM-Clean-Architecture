package com.example.mvvmapplication.data.remote

import com.example.mvvmapplication.repository.RemoteRepository
import com.example.mvvmapplication.data.remote.RestaurantService

class RemoteRepositoryImpl(private val restaurantService: RestaurantService) : RemoteRepository {
    override fun getRestaurantService(): RestaurantService = restaurantService
}