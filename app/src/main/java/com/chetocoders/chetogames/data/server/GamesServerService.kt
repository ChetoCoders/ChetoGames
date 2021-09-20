package com.chetocoders.chetogames.data.server

import com.chetocoders.chetogames.data.server.dto.GameDTO
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface GamesServerService {

    @POST("/games")
    suspend fun getGames(@Body query: RequestBody): List<GameDTO>

}
