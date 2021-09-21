package com.chetocoders.chetogames.data.server.mapper

import com.chetocoders.chetogames.data.server.dto.GamemodeDTO
import com.chetocoders.domain.GameMode as GameModeDomain

fun GamemodeDTO.toDomain() = GameModeDomain(
    this.id,
    this.name
)

fun GameModeDomain.toGameModeDTO() = GamemodeDTO(
    this.id,
    this.name
)