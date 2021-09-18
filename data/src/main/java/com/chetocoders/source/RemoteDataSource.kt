package com.chetocoders.source

import com.chetocoders.domain.GameDetail

interface RemoteDataSource {

    suspend fun getGames(): List<GameDetail>
}