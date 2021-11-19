package com.chetocoders.chetogames.ui.gameCatalog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chetocoders.chetogames.common.*
import com.chetocoders.domain.GameDetail
import com.chetocoders.usecases.GetGamesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class GameCatalogIntegrationTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getGamesUseCase: GetGamesUseCase

    @Mock
    lateinit var localDataSource: FakeLocalDataSource

    @Mock
    lateinit var remoteDataSource: FakeRemoteDataSource

    private lateinit var vm: GameCatalogViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        vm = GameCatalogViewModel(coroutineTestRule.testDispatcher, getGamesUseCase)
    }

  @ExperimentalCoroutinesApi
    @Test
    fun `get games catalog local`() {

        coroutineTestRule.testDispatcher.runBlockingTest {
            val fakesLocalGames = listOf(mockedGameDetail.copy(1))
            whenever(getGamesUseCase.invoke()).thenReturn(fakesLocalGames)

            var games = listOf<GameDetail>()
            val job = launch {
                vm.requestListGame()
                games = vm.games.value
            }

            localDataSource.games = fakesLocalGames
            Assert.assertTrue(games[0] == fakesLocalGames[0])

            job.cancel()

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get games catalog remote`() {

        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(getGamesUseCase.invoke()).thenReturn(defaultFakeGames)

            remoteDataSource.games = defaultFakeGames

            var games = listOf<GameDetail>()
            val job = launch {
                vm.requestListGame()
                games = vm.games.value
            }

            Assert.assertTrue(games[0] == defaultFakeGames[0])

            job.cancel()

        }
    }

}