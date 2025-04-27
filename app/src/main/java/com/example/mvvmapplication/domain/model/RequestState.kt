package com.example.mvvmapplication.domain.model

sealed class RequestState {
    data object Loading : RequestState()
    data class Success(val response: RestaurantResponse) : RequestState()
    data class Error(val error: String) : RequestState()
}