package com.chetocoders.chetogames.data.server

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GamesServerService {

    @GET("games")
    suspend fun getGames(): TokenDTO

}