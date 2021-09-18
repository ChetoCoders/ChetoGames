package source

import Game
import java.util.*

interface RemoteDataSource {

    suspend fun getGames(): List<Game>
}