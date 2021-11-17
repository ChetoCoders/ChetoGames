package com.chetocoders.chetogames.data.database.mapper

import com.chetocoders.chetogames.data.database.entity.GameMode as GameModeEntity
import com.chetocoders.domain.GameMode as GameModeDomain

fun GameModeEntity.toDomain() = GameModeDomain(
    this.gameModeId,
    this.name
)

fun GameModeDomain.toEntity() = GameModeEntity(
    this.id,
    this.name
)