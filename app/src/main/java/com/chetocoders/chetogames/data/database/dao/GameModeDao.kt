package com.chetocoders.chetogames.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.chetocoders.chetogames.data.database.entity.GameMode
import com.chetocoders.chetogames.data.database.entity.GameModeGameRef


@Dao
interface GameModeDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gameMode: GameMode): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAssignedGameMode( gameModeGameRef: GameModeGameRef)
}
