package com.chetocoders.chetogames.ui.splash

import com.chetocoders.data.repository.GameRepository
import com.chetocoders.usecases.LoadGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class SplashFragmentModule {

    @Provides
    @ViewModelScoped
    fun loadGamesUseCaseProvider(gameRepository: GameRepository) =
        LoadGamesUseCase(gameRepository)
}