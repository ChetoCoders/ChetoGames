package com.chetocoders.chetogames.data.database

import com.chetocoders.chetogames.data.database.entity.AgeRatingGameRef
import com.chetocoders.chetogames.data.database.entity.GameModeGameRef
import com.chetocoders.chetogames.data.database.entity.GenreGameRef
import com.chetocoders.chetogames.data.database.entity.PlatformGameRef
import com.chetocoders.chetogames.data.database.mapper.toDomain
import com.chetocoders.chetogames.data.database.mapper.toEntity
import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.domain.GameDetail

class RoomDataSource(db: GameDatabase) : LocalDataSource {

    private val ageRatingDao = db.ageRatingDao()
    private val gameDao = db.gameDao()
    private val gameModeDao = db.gameModeDao()
    private val genreDao = db.genreDao()
    private val imageDao = db.imageDao()
    private val platformDao = db.platformDao()

    override suspend fun getGameDetails(): List<GameDetail> {
        return gameDao.getAll().map { it.toDomain() }
    }

    override suspend fun getGameDetail(gameId: Long): GameDetail {
        return gameDao.getById(gameId).toDomain()
    }

    override suspend fun insertGame(game: GameDetail) {
        game.id = gameDao.insert(game.toEntity().game)
        game.ageRatings?.forEach { ageRating ->
            ageRating.id = ageRatingDao.insert(ageRating.toEntity())
            ageRatingDao.addAssignedAgeRating(AgeRatingGameRef(ageRating.id!!, game.id!!))
        }
        game.platforms?.forEach { platform ->
            platform.id = platformDao.insert(platform.toEntity())
            platformDao.addAssignedPlatform(PlatformGameRef(platform.id!!, game.id!!))
        }
        game.genres?.forEach { genre ->
            genre.id = genreDao.insert(genre.toEntity())
            genreDao.addAssignedGenre(GenreGameRef(genre.id!!, game.id!!))
        }
        game.gameModes?.forEach { gameMode ->
            gameMode.id = gameModeDao.insert(gameMode.toEntity())
            gameModeDao.addAssignedGameMode(GameModeGameRef(gameMode.id!!, game.id!!))
        }
        game.cover?.let { imageDao.insert(it.toEntity()) }
        game.screenshots?.let { imageDao.insertAll(it.map { image -> image.toEntity() }) }
    }

    override suspend fun updateGame(game: GameDetail) {
        gameDao.update(game.toEntity().game)
    }

    override suspend fun insertGames(gameList: List<GameDetail>) {
        gameList.forEach { insertGame(it) }
    }
}