package com.chetocoders.usecases

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.repository.GameRepository
import com.chetocoders.domain.GameDetail

class GetGameUseCase(private val gameRepository: GameRepository) {
    suspend fun invoke(gameId: Long): ResultData<GameDetail> = gameRepository.getGame(gameId)
}