package com.example.mvvmapplication.domain.usecase

import com.example.mvvmapplication.data.repository.Repository
import com.example.mvvmapplication.domain.model.RequestState

class GetRestaurantUseCase(private val repository: Repository) {

    suspend fun getRestaurant(): RequestState {
        val response = repository.remoteRepository().getRestaurantService().getRestaurantList()
        if (response.isSuccessful) {
            val restaurantResponse = response.body()
            restaurantResponse?.let {
                return RequestState.Success(it)
            } ?: run {
                return RequestState.Error("Something went wrong!")
            }
        } else {
            return RequestState.Error(response.message())
        }
    }
}