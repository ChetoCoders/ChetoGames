package com.chetocoders.chetogames.data.server

import com.chetocoders.chetogames.data.server.mapper.toDomain
import com.chetocoders.data.common.Response
import com.chetocoders.data.source.RemoteDataSource
import com.chetocoders.domain.GameDetail
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class GameServerDataSource : RemoteDataSource {

    override suspend fun getGames(): Response<List<GameDetail>> {
        return try {
            val response = GamesServer.service.getGames(
                Queries.GET_GAMES.toRequestBody(ServerConstants.TYPE_TEXT_PLAIN.toMediaTypeOrNull())
            )
            Response(value = response.body()?.map { it.toDomain() } ?: emptyList())
        } catch (e: Exception) {
            Response(error = e.cause)
        }
    }
}