package com.chetocoders.chetogames.data.server

import com.chetocoders.chetogames.data.server.dto.GameDTOResult
import retrofit2.http.Body
import retrofit2.http.GET

interface GamesServerService {

    @GET("games")
    suspend fun getGames(@Body query:String): GameDTOResult

}