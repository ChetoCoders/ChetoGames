package com.chetocoders.chetogames.data.server.mapper

import com.chetocoders.chetogames.data.server.dto.GamemodeDTO
import com.chetocoders.domain.Gamemode as GamemodeDomain

fun GamemodeDTO.toDomain() = GamemodeDomain(
    this.id,
    this.name
)

fun GamemodeDomain.toGameModeDTO() = GamemodeDTO(
    this.id,
    this.name
)