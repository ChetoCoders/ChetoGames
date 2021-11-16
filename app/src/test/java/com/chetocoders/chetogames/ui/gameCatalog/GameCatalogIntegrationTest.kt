package com.chetocoders.chetogames.ui.gameCatalog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chetocoders.chetogames.common.*
import com.chetocoders.usecases.GetGamesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


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
    fun `get games catalog`() {

        coroutineTestRule.testDispatcher.runBlockingTest {

            val fakesLocalGames = listOf(mockedGameDetail.copy(1))

            localDataSource.games = fakesLocalGames

            val states = arrayListOf<GameCatalogViewModel.UiModel>()
            val job = launch {
                vm.viewState.toList(states)
            }

            Assert.assertTrue(states.equals(GameCatalogViewModel.UiModel.Content(fakesLocalGames)))

            job.cancel()

        }
    }

}