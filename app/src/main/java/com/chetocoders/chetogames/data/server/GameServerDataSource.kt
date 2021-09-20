package com.chetocoders.chetogames.data.server

import com.chetocoders.chetogames.data.server.mapper.toDomain
import com.chetocoders.domain.GameDetail
import com.chetocoders.data.source.RemoteDataSource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class GameServerDataSource : RemoteDataSource {


    override suspend fun getGames(): List<GameDetail> =
        GamesServer.service
            .getGames(
                Constants.GAME_DETAILS_QUERY
                    .toRequestBody(Constants.TYPE_TEXT_PLAIN.toMediaTypeOrNull())
            )
            .map {
                it.toDomain() }

}