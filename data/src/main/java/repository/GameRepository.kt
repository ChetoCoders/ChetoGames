package repository

import source.RemoteDataSource
import java.util.*

class GameRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getGames(): List<Objects>{
        val games = remoteDataSource.getGames()
        return games;
    }

}