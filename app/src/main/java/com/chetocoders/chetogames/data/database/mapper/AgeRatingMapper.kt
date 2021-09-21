package com.chetocoders.chetogames.data.database.mapper

import com.chetocoders.domain.AgeRating as AgeRatingDomain
import com.chetocoders.domain.AgeRatingCategory
import com.chetocoders.domain.Rating
import com.chetocoders.chetogames.data.database.entity.AgeRating as AgeRatingEntity

fun AgeRatingEntity.toDomain() = AgeRatingDomain(
    this.ageRatingId,
    AgeRatingCategory.get(this.category),
    Rating.get(this.rating)
)

fun AgeRatingDomain.toEntity() = AgeRatingEntity(
    this.id,
    this.category?.index?.toInt(),
    this.rating?.index?.toInt()
)