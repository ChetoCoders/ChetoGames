package com.chetocoders.chetogames.ui


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import com.chetocoders.data.repository.GameRepository
import com.chetocoders.usecases.GetGames


@Module
@InstallIn(ViewModelComponent::class)
class GameCatalogFragment {
    @Provides
    @ViewModelScoped
    fun getGamesProvider(gameRepository: GameRepository) =
        GetGames(gameRepository)
}
