package com.example.mvvmapplication.di

import com.example.mvvmapplication.data.local.LocalRepositoryImpl
import com.example.mvvmapplication.data.remote.RemoteRepositoryImpl
import com.example.mvvmapplication.data.repository.Repository
import com.example.mvvmapplication.data.remote.RestaurantService
import com.example.mvvmapplication.domain.usecase.GetRestaurantUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRestaurantService(retrofit: Retrofit): RestaurantService {
        return retrofit.create<RestaurantService>(RestaurantService::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pastebin.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRepository(restaurantService: RestaurantService): Repository {
        return Repository(RemoteRepositoryImpl(restaurantService), LocalRepositoryImpl())
    }

    @Provides
    fun provideRestaurantUseCase(repository: Repository): GetRestaurantUseCase {
        return GetRestaurantUseCase(repository)
    }

}