package com.chetocoders.usecases

import com.chetocoders.data.repository.GameRepository
import com.chetocoders.domain.GameDetail

class AddGameUseCase(private val gameRepository: GameRepository) {

    suspend operator fun invoke(game: GameDetail) : GameDetail=
        gameRepository.addGame(game)
}