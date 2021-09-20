package com.chetocoders.chetogames.data.server

import com.chetocoders.chetogames.data.server.dto.GameDTO
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GamesServerService {

    @POST("games")
    suspend fun getGames(@Body query: RequestBody): Response<List<GameDTO>>
}
