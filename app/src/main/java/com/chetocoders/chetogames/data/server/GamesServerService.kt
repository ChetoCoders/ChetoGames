package com.chetocoders.chetogames.data.server

import com.chetocoders.chetogames.data.server.dto.GameDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface GamesServerService {

    @POST("games")
    suspend fun getGames(@Body query: String): List<GameDTO>

}