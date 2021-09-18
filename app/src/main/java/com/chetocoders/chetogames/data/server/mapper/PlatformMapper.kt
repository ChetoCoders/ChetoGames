package com.chetocoders.chetogames.data.server.mapper

import com.chetocoders.chetogames.data.server.dto.PlatformDTO
import com.chetocoders.domain.Platform as PlatformDomain

fun PlatformDTO.toDomain() = PlatformDomain(
    this.id,
    this.name
)

fun PlatformDomain.toPlatformDTO() = PlatformDTO(
    this.id,
    this.name
)