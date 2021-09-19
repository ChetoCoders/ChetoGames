package com.chetocoders.chetogames.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chetocoders.domain.Game

@Entity
data class Image (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val game: Game?,
    val alphaChannel: Boolean?,
    val animated: Boolean?,
    val url: String?,
    val height: Int?,
    val width: Int?
)