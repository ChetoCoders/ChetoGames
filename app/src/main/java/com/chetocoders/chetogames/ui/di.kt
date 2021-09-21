package com.chetocoders.chetogames.ui


import com.chetocoders.data.repository.GameRepository
import com.chetocoders.usecases.GetGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class GameCatalogFragment {

    @Provides
    @ViewModelScoped
    fun getGamesUseCaseProvider(gameRepository: GameRepository) = GetGamesUseCase(gameRepository)
}