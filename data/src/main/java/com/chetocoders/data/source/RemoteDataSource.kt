package com.chetocoders.data.source

import com.chetocoders.domain.GameDetail

interface RemoteDataSource {

    // TODO: Sacar de la rama
    suspend fun getGames(): List<GameDetail>
}