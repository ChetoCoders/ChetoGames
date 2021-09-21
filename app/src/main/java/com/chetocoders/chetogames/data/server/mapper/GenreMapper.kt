package com.chetocoders.chetogames.data.server.mapper

import com.chetocoders.chetogames.data.server.dto.GenreDTO
import com.chetocoders.domain.Genre as GenreDomain

fun GenreDTO.toDomain() = GenreDomain(
    this.id,
    this.name
)

fun GenreDomain.toGenreDTO() = GenreDTO(
    this.id,
    this.name
)