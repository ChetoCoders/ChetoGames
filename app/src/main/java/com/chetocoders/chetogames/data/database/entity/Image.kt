package com.chetocoders.chetogames.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Image (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val gameId: Long?,
    val alphaChannel: Boolean?,
    val animated: Boolean?,
    val url: String?,
    val height: Int?,
    val width: Int?,
    val isCover: Boolean?
)