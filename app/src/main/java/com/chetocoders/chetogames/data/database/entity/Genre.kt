package com.chetocoders.chetogames.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genre (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String?
)