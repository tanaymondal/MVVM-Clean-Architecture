package com.example.mvvmapplication.data.repository

import com.example.mvvmapplication.repository.LocalRepository
import com.example.mvvmapplication.repository.RemoteRepository

class Repository(private val remoteRepository: RemoteRepository, private val localRepository: LocalRepository) {
    fun remoteRepository(): RemoteRepository = remoteRepository

    fun localRepository(): LocalRepository = localRepository
}