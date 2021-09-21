package com.chetocoders.chetogames.di

import com.chetocoders.chetogames.data.server.GameServerDataSource
import com.chetocoders.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = GameServerDataSource()
}