package com.chetocoders.data.repository

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.domain.GameMode

class GameModesRepository(private val localDataSource: LocalDataSource) {
    suspend fun getGameModes(): ResultData<List<GameMode>> {
        return try {
            ResultData.success(localDataSource.getGameModes())
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }
}