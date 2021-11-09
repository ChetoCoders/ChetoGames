package com.chetocoders.chetogames.ui.gameCatalog

import com.chetocoders.data.repository.GameRepository
import com.chetocoders.data.repository.RegionRepository
import com.chetocoders.usecases.GetGamesUseCase
import com.chetocoders.usecases.GetRegionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class GameCatalogFragmentModule {

    @Provides
    @ViewModelScoped
    fun getGamesUseCaseProvider(gameRepository: GameRepository) = GetGamesUseCase(gameRepository)

    @Provides
    @ViewModelScoped
    fun getRegionUseCaseProvider(regionRepository : RegionRepository) = GetRegionUseCase(regionRepository)
}