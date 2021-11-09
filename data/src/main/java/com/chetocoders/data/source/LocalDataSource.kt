package com.chetocoders.data.source

import com.chetocoders.domain.GameDetail
import com.chetocoders.domain.GameMode
import com.chetocoders.domain.Genre
import com.chetocoders.domain.Platform

interface LocalDataSource {

    suspend fun getGameDetails() : List<GameDetail>
    suspend fun getGameDetail(gameId: Long) : GameDetail
    suspend fun getGenres(): List<Genre>
    suspend fun getPlatforms(): List<Platform>
    suspend fun getGameModes(): List<GameMode>
    suspend fun insertGames(gameList: List<GameDetail>)
    suspend fun insertGame(game: GameDetail)
    suspend fun updateGame(game: GameDetail)
}