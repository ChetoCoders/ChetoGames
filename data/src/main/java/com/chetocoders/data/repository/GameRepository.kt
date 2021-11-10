package com.chetocoders.data.repository

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.data.source.RemoteDataSource
import com.chetocoders.domain.GameDetail

class GameRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource) {

    suspend fun loadGames(): ResultData<Boolean> {
        return if(localDataSource.getGameDetails().isEmpty()) {
            val gameDetails = remoteDataSource.getGames().getValue()
            if(!gameDetails.isNullOrEmpty()){
                localDataSource.insertGames(gameDetails)
                ResultData.Success(value = true)
            } else {
                ResultData.Success(value = false)
            }
        } else {
            ResultData.Success(value = true)
        }
    }

    suspend fun getGames(): List<GameDetail> {
        return localDataSource.getGameDetails()
    }

    suspend fun getGame(gameId: Long): ResultData<GameDetail> {
        return try {
            ResultData.Success(value = localDataSource.getGameDetail(gameId))
        } catch (e: Exception) {
            ResultData.Failure(e)
        }
    }

    suspend fun updateGame(game: GameDetail): ResultData<GameDetail> {
        return try {
            localDataSource.updateGame(game)
            ResultData.Success(value = game)
        } catch (e: Exception) {
            ResultData.Failure(e)
        }
    }

    suspend fun addGame(game: GameDetail) : ResultData<GameDetail> {
        return try {
            localDataSource.insertGame(game)
            ResultData.Success(value = game)
        } catch (e: Exception) {
            ResultData.Failure(e)
        }
    }
}