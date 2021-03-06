package com.chetocoders.chetogames.data.database.dao

import androidx.room.*
import com.chetocoders.chetogames.data.database.entity.Game
import com.chetocoders.chetogames.data.database.entity.GameDetail

@Dao
interface GameDao {

    @Transaction
    @Query("SELECT * FROM Game LIMIT 100")
    fun getAll(): List<GameDetail>


    @Transaction
    @Query("SELECT * FROM Game WHERE gameId = :id")
    fun getById(id: Long): GameDetail

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(game: Game): Long

    @Transaction
    @Update
    fun update(game: Game)
}