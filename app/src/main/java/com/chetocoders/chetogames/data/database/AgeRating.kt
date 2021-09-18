package com.chetocoders.chetogames.data.database

import androidx.room.Entity
import com.chetocoders.domain.AgeRatingCategory
import com.chetocoders.domain.Rating

@Entity
data class AgeRating (
    val id: Int?,
    val category: AgeRatingCategory?,
    val rating: Rating?
)