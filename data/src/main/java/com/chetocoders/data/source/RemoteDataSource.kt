package com.chetocoders.data.source

import com.chetocoders.data.common.Response
import com.chetocoders.domain.GameDetail

interface RemoteDataSource {

    suspend fun getGames(): Response<List<GameDetail>>
}