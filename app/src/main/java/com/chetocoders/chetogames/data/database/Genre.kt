package com.chetocoders.chetogames.data.database

import androidx.room.Entity

@Entity
data class Genre (
    val id: Int?,
    val name: String?
)