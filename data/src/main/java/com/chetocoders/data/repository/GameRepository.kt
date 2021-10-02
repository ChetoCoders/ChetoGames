package com.chetocoders.data.repository

import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.data.source.RemoteDataSource
import com.chetocoders.domain.GameDetail

class GameRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource) {

    suspend fun getGames(): List<GameDetail> {
        if(localDataSource.getGameDetails().isEmpty()){
            val gameDetails = remoteDataSource.getGames().getValue()
            if(!gameDetails.isNullOrEmpty()){
                localDataSource.insertGames(gameDetails)
            }
        }
        return localDataSource.getGameDetails()
    }
}