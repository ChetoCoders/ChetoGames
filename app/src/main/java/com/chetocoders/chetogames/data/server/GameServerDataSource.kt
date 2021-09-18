package com.chetocoders.chetogames.data.server

import com.chetocoders.chetogames.data.server.mapper.toDomain
import com.chetocoders.domain.GameDetail
import com.chetocoders.source.RemoteDataSource

class GameServerDataSource : RemoteDataSource {

    override suspend fun getGames(): List<GameDetail> =
        GamesServer.service
            .getGames(ApiQueryConstants.GAME_DETAILS_QUERY)
            .result
            .map { it.toDomain() }

}