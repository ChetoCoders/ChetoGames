package com.chetocoders.chetogames.data.database

import com.chetocoders.data.LocalDataSource

class RoomDataSource(db: GameDatabase) : LocalDataSource {

    private val ageRatingDao = db.ageRatingDao()
    private val gameDao = db.gameDao()
    private val gamemodeDao = db.gameModeDao()
    private val genreDao = db.genreDao()
    private val imageDao = db.imageDao()
    private val platformDao = db.platformDao()
}