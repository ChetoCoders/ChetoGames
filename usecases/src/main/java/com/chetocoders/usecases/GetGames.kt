package com.chetocoders.usecases

import com.chetocoders.domain.GameDetail
import com.chetocoders.repository.GameRepository

class GetGames(private val gameRepository: GameRepository) {
    suspend fun invoke(): List<GameDetail> = gameRepository.getGames()
}