package com.chetocoders.usecases

import com.chetocoders.data.repository.GameRepository
import com.chetocoders.domain.GameDetail

class GetGamesUseCase(private val gameRepository: GameRepository) {
    suspend fun invoke(): List<GameDetail> = gameRepository.getGames()
}