package com.chetocoders.repository

import com.chetocoders.domain.GameDetail
import com.chetocoders.source.RemoteDataSource

class GameRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getGames(): List<GameDetail> {
        return remoteDataSource.getGames();
    }

}