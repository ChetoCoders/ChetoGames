package com.chetocoders.chetogames.data.server.mapper

import com.chetocoders.chetogames.data.server.dto.CoverDTO
import com.chetocoders.domain.Game as GameDomain
import com.chetocoders.domain.Image as ImageDomain

fun CoverDTO.toDomain() = ImageDomain(
    this.id,
    GameDomain(this.game),
    this.alphaChannel,
    this.animated,
    this.url,
    this.height,
    this.width,
    true
)

fun ImageDomain.toCoverDTO() = CoverDTO(
    this.id,
    this.alphaChannel,
    this.animated,
    null,
    this.height,
    null,
    this.url,
    this.width,
)