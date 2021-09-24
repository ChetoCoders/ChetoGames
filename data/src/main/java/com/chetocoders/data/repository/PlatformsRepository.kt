package com.chetocoders.data.repository

import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.domain.Platform

class PlatformsRepository(private val localDataSource: LocalDataSource) {
    suspend fun getPlatforms(): List<Platform> = localDataSource.getPlatforms()
}