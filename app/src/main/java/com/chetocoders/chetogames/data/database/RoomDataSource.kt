package com.chetocoders.chetogames.data.database

import com.chetocoders.chetogames.data.database.dao.GameModeGameRefDao
import com.chetocoders.data.LocalDataSource

class RoomDataSource(db: GameDatabase) : LocalDataSource {

    private val ageRatingDao = db.ageRatingDao()
    private val ageRatingGameRefDao = db.ageRatingGameRefDao()
    private val gameDao = db.gameDao()
    private val gameModeDao = db.gameModeDao()
    private val gameModeGameRefDao = db.gameModeGameRefDao()
    private val genreDao = db.genreDao()
    private val genreGameRefDao = db.genreGameRefDao()
    private val imageDao = db.imageDao()
    private val platformDao = db.platformDao()
    private val platformGameRefDao = db.platformGameRefDao()
}