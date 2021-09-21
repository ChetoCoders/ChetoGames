package com.chetocoders.chetogames.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AgeRating (
    @PrimaryKey(autoGenerate = true) val ageRatingId: Long?,
    val category: Int?,
    val rating: Int?
)

@Entity(primaryKeys = ["ageRatingId", "gameId"])
data class AgeRatingGameRef (
    val ageRatingId: Long,
    val gameId: Long
)