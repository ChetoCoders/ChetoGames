package com.chetocoders.chetogames.ui.gameCatalog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chetocoders.chetogames.common.CoroutinesTestRule
import com.chetocoders.chetogames.common.mockedGameDetail
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
class GameCatalogViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getGamesUseCase: GetGamesUseCase

    private lateinit var vm: GameCatalogViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        vm = GameCatalogViewModel(coroutineTestRule.testDispatcher, getGamesUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `check loading catalog viewmodel`() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            val gameDetails = listOf(mockedGameDetail.copy(27))
            whenever(getGamesUseCase.invoke()).thenReturn(gameDetails)

            var loading = true
            val job = launch {
                loading =  vm.loading.value
            }

            vm.requestListGame()
            Assert.assertTrue(!loading)
            job.cancel()

        }
    }
    @ExperimentalCoroutinesApi
    @Test
    fun `get games catalog viewmodel`() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            val gameDetails = listOf(mockedGameDetail.copy(27))
            whenever(getGamesUseCase.invoke()).thenReturn(gameDetails)

            var games = listOf<GameDetail>()
            val job = launch {
                games =  vm.games.value
            }

            vm.requestListGame()
            Assert.assertTrue(games.isEmpty())
            job.cancel()

        }
    }

}