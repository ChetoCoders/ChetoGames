package com.chetocoders.data.repository

import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.domain.GameMode

class GameModesRepository(private val localDataSource: LocalDataSource) {
    suspend fun getGameModes(): List<GameMode> = localDataSource.getGameModes()
}