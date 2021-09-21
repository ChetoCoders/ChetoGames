package com.chetocoders.usecases

import com.chetocoders.data.common.ResultData
import com.chetocoders.domain.GameDetail
import com.chetocoders.data.repository.GameRepository

// TODO: Sacar de la rama
class GetGamesUseCase(private val gameRepository: GameRepository) {
    suspend fun invoke(): ResultData<List<GameDetail>> = gameRepository.getGames()
}