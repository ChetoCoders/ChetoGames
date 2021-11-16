package com.chetocoders.usecases

import com.chetocoders.data.repository.GameRepository
import com.chetocoders.domain.Game
import com.chetocoders.domain.GameCategory
import com.chetocoders.domain.GameDetail
import com.chetocoders.domain.Image
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetGamesUseCaseTest {

    @Mock
    lateinit var gameRepository: GameRepository

    private lateinit var getGames: GetGamesUseCase

    @Before
    fun setUp() {
        getGames = GetGamesUseCase(gameRepository)
    }

    @Test
    fun `invoke calls game repository`() {
        runBlocking {

            val games = listOf(mockedGameDetail.copy(id = 27))
            whenever(gameRepository.getGames()).thenReturn(games)

            val result = getGames.invoke()

            Assert.assertEquals(games, result)
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
                isCover = false
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
                    isCover = false
                )
            ),
            emptyList(),
            isExternal = false,
            isFavourite = false
        )
}