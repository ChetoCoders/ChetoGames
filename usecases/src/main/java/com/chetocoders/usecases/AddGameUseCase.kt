package com.chetocoders.usecases

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.repository.GameRepository
import com.chetocoders.domain.GameDetail

class AddGameUseCase(private val gameRepository: GameRepository) {

    suspend operator fun invoke(game: GameDetail) : ResultData<GameDetail> =
        gameRepository.addGame(game)
}