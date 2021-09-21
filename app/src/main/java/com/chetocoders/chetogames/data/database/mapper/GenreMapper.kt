package com.chetocoders.chetogames.data.database.mapper

import com.chetocoders.domain.Genre as GenreDomain
import com.chetocoders.chetogames.data.database.entity.Genre as GenreEntity

fun GenreEntity.toDomain() = GenreDomain(
    this.genreId,
    this.name
)

fun GenreDomain.toEntity() = GenreEntity(
    this.id,
    this.name
)