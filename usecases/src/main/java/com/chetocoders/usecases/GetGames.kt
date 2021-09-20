package com.chetocoders.usecases

import com.chetocoders.domain.GameDetail
import com.chetocoders.data.repository.GameRepository

// TODO: Sacar de la rama
class GetGames(private val gameRepository: GameRepository) {
    suspend fun invoke(): List<GameDetail> = gameRepository.getGames()
}