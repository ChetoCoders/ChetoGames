package com.chetocoders.chetogames.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.chetocoders.chetogames.data.database.entity.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM Game")
    fun getAll(): List<Game>
}