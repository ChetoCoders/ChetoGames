package com.chetocoders.chetogames.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Platform (
    @PrimaryKey(autoGenerate = true) val platformId: Long,
    val name: String?
)

@Entity(primaryKeys = ["platformId", "gameId"])
data class PlatformGameRef (
    val platformId: Long,
    val gameId: Long
)