package com.chetocoders.data.repository

import com.chetocoders.data.common.Response
import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.data.source.RemoteDataSource
import com.chetocoders.domain.Game
import com.chetocoders.domain.GameCategory
import com.chetocoders.domain.GameDetail
import com.chetocoders.domain.Image
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GameRepositoryTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    private lateinit var gameRepository: GameRepository

    @Before
    fun setUp() {
        gameRepository = GameRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `getGames gets from local data source`() {
        runBlocking {
            val localGames = listOf(mockedGameDetail.copy(27))
            whenever(!localDataSource.getGameDetails().isNullOrEmpty()).thenReturn(true)
            whenever(localDataSource.getGameDetails()).thenReturn(localGames)

            val resultLocalData = gameRepository.getGames()

            assertEquals(localGames, resultLocalData)
        }

    }

    @Test
    fun `getGames saves remote data to local`() {
        runBlocking {

            val remoteGames = listOf(mockedGameDetail.copy(27))
            whenever(localDataSource.getGameDetails()).thenReturn(emptyList())
            whenever(remoteDataSource.getGames()).thenReturn(Response(value = remoteGames))
            gameRepository.getGames()
            verify(localDataSource).insertGames(remoteGames)
        }
    }

    private val mockedGameDetail =
        GameDetail(
            23635,
            "Dragon Atlas", null,
            null,
            GameCategory.MAIN_GAME,
            emptyList(),
            emptyList(),
            emptyList(),
            Image(
                31348,
                Game(
                    23635,
                    null,
                    null,
                    null,
                    isExternal = false,
                    isFavourite = false
                ),
                null,
                null,
                "//images.igdb.com/igdb/image/upload/t_thumb/kyquup8cobqhze6u9mum.jpg",
                374,
                666,
                false
            ),
            listOf(
                Image(
                    31348,
                    Game(
                        23635,
                        null,
                        null,
                        null,
                        isExternal = false,
                        isFavourite = false
                    ),
                    null,
                    null,
                    "//images.igdb.com/igdb/image/upload/t_thumb/kyquup8cobqhze6u9mum.jpg",
                    374,
                    666,
                    false
                )
            ),
            emptyList(),
            isExternal = false,
            isFavourite = false
        )
}