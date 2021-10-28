package com.chetocoders.chetogames.data.database.mapper

import com.chetocoders.chetogames.data.database.entity.Platform as PlatformEntity
import com.chetocoders.domain.Platform as PlatformDomain

fun PlatformEntity.toDomain() = PlatformDomain(
    this.platformId,
    this.name
)

fun PlatformDomain.toEntity() = PlatformEntity(
    this.id,
    this.name
)