package com.chetocoders.chetogames.di


import com.chetocoders.chetogames.data.server.GameServerDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.chetocoders.source.RemoteDataSource

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = GameServerDataSource()

}