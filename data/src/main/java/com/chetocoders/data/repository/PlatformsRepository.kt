package com.chetocoders.data.repository

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.domain.Platform

class PlatformsRepository(private val localDataSource: LocalDataSource) {
    suspend fun getPlatforms(): ResultData<List<Platform>> {
        return try {
            ResultData.success(localDataSource.getPlatforms())
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }
}