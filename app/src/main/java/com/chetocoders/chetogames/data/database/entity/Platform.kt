package com.chetocoders.chetogames.data.database.entity

import androidx.room.PrimaryKey

data class Platform (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String?
)