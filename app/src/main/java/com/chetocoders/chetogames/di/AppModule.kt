package com.chetocoders.chetogames.di

import android.content.Context
import com.chetocoders.chetogames.data.database.GameDatabase
import com.chetocoders.chetogames.data.database.RoomDataSource
import com.chetocoders.chetogames.data.server.*
import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun okHttpProvider() =
        HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header(ServerConstants.HEADER_CLIENT_ID, CLIENT_ID)
                builder.header(ServerConstants.HEADER_AUTHORIZATION, AUTHORIZATION)

                return@Interceptor chain.proceed(builder.build())
            }).build()
        }

    @Singleton
    @Provides
    fun retrofitProvider(okHttpClient: OkHttpClient): GameServerService = Retrofit.Builder()
        .baseUrl(ServerConstants.URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create(GameServerService::class.java)
        }

    @Provides
    fun gameDataSourceProvider(gameServerService: GameServerService): RemoteDataSource = GameServerDataSource(gameServerService)

    @Provides
    fun gameDatabaseProvider(@ApplicationContext context: Context): GameDatabase =
        GameDatabase.build(context)

    @Provides
    fun localDataSourceProvider(gameDatabase: GameDatabase): LocalDataSource =
        RoomDataSource(gameDatabase)

}