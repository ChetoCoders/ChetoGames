package com.chetocoders.chetogames.data.database.dao

import androidx.room.*
import com.chetocoders.chetogames.data.database.entity.GameMode
import com.chetocoders.chetogames.data.database.entity.GameModeGameRef


@Dao
interface GameModeDao {

    @Transaction
    @Query("SELECT * FROM GameMode")
    fun getAll(): List<GameMode>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gameMode: GameMode): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAssignedGameMode(gameModeGameRef: GameModeGameRef)
}
