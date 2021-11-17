package com.chetocoders.usecases

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.repository.GameRepository

class LoadGamesUseCase (private val gameRepository: GameRepository) {
    suspend fun invoke(): ResultData<Boolean> = gameRepository.loadGames()
}