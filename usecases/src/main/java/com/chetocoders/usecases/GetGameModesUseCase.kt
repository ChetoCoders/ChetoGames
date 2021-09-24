package com.chetocoders.usecases

import com.chetocoders.data.repository.GameModesRepository
import com.chetocoders.domain.GameMode

class GetGameModesUseCase(private val gameModesRepository: GameModesRepository) {
    suspend fun invoke(): List<GameMode> = gameModesRepository.getGameModes()
}