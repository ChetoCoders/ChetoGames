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

            localDataSource.games = fakesLocalGames

            val states = arrayListOf<GameCatalogViewModel.UiModel>()
            val job = launch {
                vm.requestListGame()
                vm.viewState.toList(states)
            }

            Assert.assertTrue((states[0] as GameCatalogViewModel.UiModel.Content).gameDetails == GameCatalogViewModel.UiModel.Content(fakesLocalGames).gameDetails)

            job.cancel()

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get games catalog remote`() {

        coroutineTestRule.testDispatcher.runBlockingTest {
            whenever(getGamesUseCase.invoke()).thenReturn(defaultFakeGames)

            remoteDataSource.games = defaultFakeGames

            val states = arrayListOf<GameCatalogViewModel.UiModel>()
            val job = launch {
                vm.requestListGame()
                vm.viewState.toList(states)
            }

            Assert.assertTrue((states[0] as GameCatalogViewModel.UiModel.Content).gameDetails == GameCatalogViewModel.UiModel.Content(defaultFakeGames).gameDetails)

            job.cancel()

        }
    }

}