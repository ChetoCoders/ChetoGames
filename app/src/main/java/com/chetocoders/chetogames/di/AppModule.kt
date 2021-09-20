package com.chetocoders.chetogames.di


import android.content.Context
import androidx.room.Room
import com.chetocoders.chetogames.data.database.GameDatabase
import com.chetocoders.chetogames.data.server.GameServerDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.chetocoders.data.source.RemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = GameServerDataSource()
}