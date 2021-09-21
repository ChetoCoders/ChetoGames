package com.chetocoders.chetogames.data.database.mapper

import com.chetocoders.domain.Platform as PlatformDomain
import com.chetocoders.chetogames.data.database.entity.Platform as PlatformEntity

fun PlatformEntity.toDomain() = PlatformDomain(
    this.platformId,
    this.name
)

fun PlatformDomain.toEntity() = PlatformEntity(
    this.id,
    this.name
)