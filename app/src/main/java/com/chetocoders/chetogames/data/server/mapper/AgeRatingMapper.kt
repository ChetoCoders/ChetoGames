package com.chetocoders.chetogames.data.server.mapper

import com.chetocoders.chetogames.data.server.dto.AgeRatingDTO
import com.chetocoders.domain.AgeRatingCategory
import com.chetocoders.domain.Rating
import com.chetocoders.domain.AgeRating as AgeRatingDomain

fun AgeRatingDTO.toDomain() = AgeRatingDomain (
    this.id,
    this.category?.let { AgeRatingCategory.get(it) },
    this.rating?.let { Rating.get(it) }
)

fun AgeRatingDomain.toDTO() = AgeRatingDTO(
    this.id,
    this.category?.let { it.index.toInt() },
    this.rating?.let { it.index.toInt() }
)