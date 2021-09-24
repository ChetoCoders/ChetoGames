package com.chetocoders.data.repository

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.data.source.RemoteDataSource
import com.chetocoders.domain.GameDetail

class GameRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getGames(): ResultData<List<GameDetail>> {
        return try {
            ResultData.Success(value = remoteDataSource.getGames().getValue().let {
                if (!it.isNullOrEmpty()) {
                    localDataSource.insertGames(it)
                }

                localDataSource.getGameDetails()
            })
        } catch (e: Exception) {
            ResultData.Failure(e)
        }
    }
}