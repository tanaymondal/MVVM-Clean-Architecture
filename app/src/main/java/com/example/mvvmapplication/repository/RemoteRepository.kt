package com.example.mvvmapplication.repository

import com.example.mvvmapplication.data.remote.RestaurantService

interface RemoteRepository {
    fun getRestaurantService() : RestaurantService
}