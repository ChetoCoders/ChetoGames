package com.chetocoders.chetogames.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameMode (
    @PrimaryKey(autoGenerate = true) val gameModeId: Long?,
    val name: String?
)


@Entity(primaryKeys = ["gameModeId", "gameId"])
data class GameModeGameRef (
    val gameModeId: Long,
    val gameId: Long
)