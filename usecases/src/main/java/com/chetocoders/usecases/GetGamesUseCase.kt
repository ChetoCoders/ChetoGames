package com.chetocoders.usecases

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.repository.GameRepository
import com.chetocoders.domain.GameDetail

// TODO: Sacar de la rama
class GetGamesUseCase(private val gameRepository: GameRepository) {
    suspend fun invoke(): ResultData<List<GameDetail>> = gameRepository.getGames()
}