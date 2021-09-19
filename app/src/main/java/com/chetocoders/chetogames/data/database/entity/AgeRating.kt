package com.chetocoders.chetogames.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chetocoders.domain.AgeRatingCategory
import com.chetocoders.domain.Rating

@Entity
data class AgeRating (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val category: AgeRatingCategory?,
    val rating: Rating?
)