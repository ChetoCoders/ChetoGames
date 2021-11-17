package com.chetocoders.chetogames.common

import com.chetocoders.chetogames.di.AppModule
import com.chetocoders.chetogames.di.DataModule
import com.chetocoders.data.common.Response
import com.chetocoders.data.repository.GameRepository
import com.chetocoders.data.repository.PermissionChecker
import com.chetocoders.data.repository.RegionRepository
import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.data.source.LocationDataSource
import com.chetocoders.data.source.RemoteDataSource
import com.chetocoders.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


val defaultFakeGames = listOf(
    mockedGameDetail.copy(1),
    mockedGameDetail.copy(2),
    mockedGameDetail.copy(3),
    mockedGameDetail.copy(4)
)

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class, DataModule::class]
)
class TestAppModule {

    @Provides
    @Singleton
    fun localDataSourceProvider(): LocalDataSource = FakeLocalDataSource()

    @Provides
    @Singleton
    fun remoteDataSourceProvider(): RemoteDataSource = FakeRemoteDataSource()

    @Provides
    @Singleton
    fun locationDataSourceProvider(): LocationDataSource = FakeLocationDataSource()

    @Provides
    @Singleton
    fun permissionCheckerProvider(): PermissionChecker = FakePermissionChecker()

    @Provides
    fun scopeViewModel(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Provides
    fun gamesRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): GameRepository {
        return GameRepository(localDataSource, remoteDataSource)
    }

    @Provides
    fun regionRepositoryProvider(
        locationDataSource: LocationDataSource,
        permissionChecker: PermissionChecker
    ) = RegionRepository(locationDataSource, permissionChecker)
}

class FakeLocalDataSource : LocalDataSource {

    var games: List<GameDetail> = emptyList()

    override suspend fun getGameDetails(): List<GameDetail> = games

    override suspend fun getGameDetail(gameId: Long): GameDetail {
        return games[0]
    }

    override suspend fun getGenres(): List<Genre> {
        return emptyList()
    }

    override suspend fun getPlatforms(): List<Platform> {
        return emptyList()
    }

    override suspend fun getGameModes(): List<GameMode> {
        return emptyList()
    }

    override suspend fun getAgeRatingsByCategory(index: Int): List<AgeRating> {
        return emptyList()
    }

    override suspend fun insertGames(gameList: List<GameDetail>) {
        games = gameList
    }

    override suspend fun insertGame(game: GameDetail) {
    }

    override suspend fun updateGame(game: GameDetail) {
    }

}

class FakeRemoteDataSource : RemoteDataSource {

    var games = defaultFakeGames

    override suspend fun getGames() = Response(value = games)
}

class FakeLocationDataSource : LocationDataSource {
    override suspend fun findLastRegion(): String {
        return "ES"
    }
}

class FakePermissionChecker : PermissionChecker {
    override suspend fun check(permission: PermissionChecker.Permission): Boolean {
        return true
    }
}