package com.chetocoders.chetogames.data.server

import com.chetocoders.chetogames.data.server.mapper.toDomain
import com.chetocoders.domain.GameDetail
import com.chetocoders.source.RemoteDataSource
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.filterList

class GameServerDataSource : RemoteDataSource {

    override suspend fun getGames(): List<GameDetail> =
        GamesServer.service
            .getGames(
                ApiQueryConstants.GAME_DETAILS_QUERY
                    .toRequestBody("text/plain".toMediaTypeOrNull())
            )
            .map {
                it.toDomain() }

}