package com.chetocoders.chetogames.ui.addgame

import com.chetocoders.data.repository.*
import com.chetocoders.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class AddGameFragmentModule {
    @Provides
    @ViewModelScoped
    fun getGenresUseCaseProvider(genreRepository: GenreRepository) =
        GetGenresUseCase(genreRepository)

    @Provides
    @ViewModelScoped
    fun getPlatformsUseCaseProvider(platformsRepository: PlatformsRepository) =
        GetPlatformsUseCase(platformsRepository)

    @Provides
    @ViewModelScoped
    fun getGameModesUseCaseProvider(gameModesRepository: GameModesRepository) =
        GetGameModesUseCase(gameModesRepository)

    @Provides
    @ViewModelScoped
    fun addGameUseCaseProvider(gameRepository: GameRepository) =
        AddGameUseCase(gameRepository)
}
