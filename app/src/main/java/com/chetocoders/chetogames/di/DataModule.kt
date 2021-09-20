package com.chetocoders.chetogames.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.chetocoders.data.repository.GameRepository
import com.chetocoders.data.source.RemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun gameRepositoryProvider( remoteDataSource: RemoteDataSource ) = GameRepository(remoteDataSource)
}