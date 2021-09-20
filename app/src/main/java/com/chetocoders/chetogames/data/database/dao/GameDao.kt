package com.chetocoders.chetogames.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.chetocoders.chetogames.data.database.entity.Game
import com.chetocoders.chetogames.data.database.entity.GameParentEntity

@Dao
interface GameDao {

    @Query("SELECT * FROM Game WHERE gameId = :id")
    fun getById(id: Int): GameParentEntity
}