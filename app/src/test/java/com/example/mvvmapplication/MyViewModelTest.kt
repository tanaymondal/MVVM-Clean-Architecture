package com.example.mvvmapplication

import com.example.mvvmapplication.domain.model.RequestState
import com.example.mvvmapplication.domain.model.RestaurantResponse
import com.example.mvvmapplication.domain.usecase.GetRestaurantUseCase
import com.example.mvvmapplication.ui.home.MyViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MyViewModelTest {

    private lateinit var viewModel: MyViewModel
    private lateinit var useCase: GetRestaurantUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        useCase = mockk()
        viewModel = MyViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadRestaurants should emit Success state`() = runTest {
        // Given
        val expectedState = RequestState.Success(RestaurantResponse(emptyList(), ""))
        coEvery { useCase.getRestaurant() } returns expectedState

        // When
        viewModel.loadRestaurants()

        // Then
        assert(viewModel.state.value == expectedState)
    }

    @Test
    fun `loadRestaurants should emit Error state`() = runTest {
        // Given
        val expectedState = RequestState.Error("Something went wrong")
        coEvery { useCase.getRestaurant() } returns expectedState

        // When
        viewModel.loadRestaurants()

        // Then
        assert(viewModel.state.value == expectedState)
    }
}