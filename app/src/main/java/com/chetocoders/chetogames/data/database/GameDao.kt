package com.chetocoders.chetogames.data.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface GameDao {
    @Query("SELECT * FROM Game")
    fun getAll(): List<Game>
}