package source

import java.util.*

interface RemoteDataSource {

    suspend fun getGames(): List<Objects>
}