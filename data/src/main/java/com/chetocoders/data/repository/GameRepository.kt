package com.chetocoders.data.repository

import com.chetocoders.domain.GameDetail
import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.data.source.RemoteDataSource

class GameRepository(
    private val remoteDataSource: RemoteDataSource) {

    // TODO: Sacar de la rama
    suspend fun getGames(): List<GameDetail> {
        return remoteDataSource.getGames();
    }

}