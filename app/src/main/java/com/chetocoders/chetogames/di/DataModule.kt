package com.chetocoders.chetogames.di

import com.chetocoders.data.repository.GameModesRepository
import com.chetocoders.data.repository.GameRepository
import com.chetocoders.data.repository.PermissionChecker
import com.chetocoders.data.repository.RegionRepository
import com.chetocoders.data.repository.GenreRepository
import com.chetocoders.data.repository.PlatformsRepository
import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.data.source.LocationDataSource
import com.chetocoders.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun gameRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = GameRepository(localDataSource, remoteDataSource)

    @Provides
    fun regionRepositoryProvider(
        locationDataSource: LocationDataSource,
        permissionChecker: PermissionChecker
    ) = RegionRepository(locationDataSource, permissionChecker)

    @Provides
    fun genreRepositoryProvider(localDataSource: LocalDataSource) = GenreRepository(localDataSource)

    @Provides
    fun platformRepositoryProvider(localDataSource: LocalDataSource) =
        PlatformsRepository(localDataSource)

    @Provides
    fun gameModeRepositoryProvider(localDataSource: LocalDataSource) =
        GameModesRepository(localDataSource)
}