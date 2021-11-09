package com.chetocoders.usecases

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.repository.GameRepository
import com.chetocoders.domain.GameDetail

class UpdateGameUseCase(private val gameRepository: GameRepository) {
    suspend fun invoke(game: GameDetail): ResultData<GameDetail> = gameRepository.updateGame(game)
}