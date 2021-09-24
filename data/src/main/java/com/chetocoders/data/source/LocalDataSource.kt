package com.chetocoders.data.source

import com.chetocoders.domain.*

interface LocalDataSource {

    suspend fun getGameDetails() : List<GameDetail>
    suspend fun getGenres() : List<Genre>
    suspend fun getPlatforms() : List<Platform>
    suspend fun getGameModes() : List<GameMode>
    suspend fun insertGames(gameList: List<GameDetail>)
    suspend fun insertGame(game: GameDetail)
}