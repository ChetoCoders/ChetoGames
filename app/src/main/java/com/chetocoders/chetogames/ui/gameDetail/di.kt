package com.chetocoders.chetogames.ui.gameDetail

import com.chetocoders.data.repository.GameRepository
import com.chetocoders.usecases.GetGameUseCase
import com.chetocoders.usecases.UpdateGameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class GameDetailFragmentModule {

    @Provides
    @ViewModelScoped
    fun getGameUseCaseProvider(gameRepository: GameRepository) = GetGameUseCase(gameRepository)

    @Provides
    @ViewModelScoped
    fun updateGameUseCaseProvider(gameRepository: GameRepository) = UpdateGameUseCase(gameRepository)
}