package com.example.mvvmapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmapplication.domain.model.RequestState
import com.example.mvvmapplication.domain.usecase.GetRestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val useCase: GetRestaurantUseCase) : ViewModel() {
    private val _state: MutableStateFlow<RequestState> = MutableStateFlow<RequestState>(
        RequestState.Loading
    )
    val state = _state.asStateFlow()

    fun loadRestaurants() {
        viewModelScope.launch {
            _state.emit(useCase.getRestaurant())
        }
    }
}