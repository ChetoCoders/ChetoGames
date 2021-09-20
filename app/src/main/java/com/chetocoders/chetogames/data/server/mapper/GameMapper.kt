package com.chetocoders.chetogames.data.server.mapper

import com.chetocoders.chetogames.data.server.dto.GameDTO
import com.chetocoders.domain.GameCategory
import com.chetocoders.domain.GameDetail

fun GameDTO.toDomain() = GameDetail(
    this.id,
    this.name,
    this.summary,
    this.releaseDates?.get(0)?.toDomain(),
    this.category?.let { GameCategory.get(it) },
    this.genres?.filterNotNull()?.map { it -> it.toDomain() },
    this.platforms?.map { it -> it.toDomain() },
    this.gameModes?.map { it -> it.toDomain() },
    this.cover?.toDomain(),
    this.screenshots?.map { it -> it.toDomain() },
    this.ageRatings?.map { it -> it.toDomain() },
    isExternal = false,
    isFavourite = false
)