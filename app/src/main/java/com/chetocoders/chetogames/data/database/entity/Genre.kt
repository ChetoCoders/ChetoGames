package com.chetocoders.chetogames.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genre (
    @PrimaryKey(autoGenerate = true) val genreId: Long,
    val name: String?
)


@Entity(primaryKeys = ["genreId", "gameId"])
data class GenreGameRef (
    val genreId: Long,
    val gameId: Long
)