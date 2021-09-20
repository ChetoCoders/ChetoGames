package com.chetocoders.chetogames.data.database

import androidx.room.Entity
import com.chetocoders.domain.Game

@Entity
data class Image (
    val id: Int?,
    val game: Game?,
    val alphaChannel: Boolean?,
    val animated: Boolean?,
    val url: String?,
    val height: Int?,
    val width: Int?
)