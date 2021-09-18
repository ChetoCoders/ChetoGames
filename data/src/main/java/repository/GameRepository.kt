package repository

import Game
import source.RemoteDataSource
import java.util.*

class GameRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getGames(): List<Game>{
        val games = remoteDataSource.getGames()
        return games;
    }

}