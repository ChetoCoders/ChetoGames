package com.chetocoders.data.source

import com.chetocoders.domain.GameDetail

interface LocalDataSource {

    suspend fun getGameDetails() : List<GameDetail>
    suspend fun insertGames(gameList: List<GameDetail>)
    suspend fun insertGame(game: GameDetail)
}