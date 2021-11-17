package com.chetocoders.chetogames.data.database.mapper

import com.chetocoders.domain.Game
import com.chetocoders.chetogames.data.database.entity.Image as ImageEntity
import com.chetocoders.domain.Image as ImageDomain

fun ImageEntity.toDomain() = ImageDomain(
    this.imageId,
    Game(id = this.gameId),
    this.alphaChannel,
    this.animated,
    this.url,
    this.height,
    this.width,
    this.isCover
)

fun ImageDomain.toEntity() = ImageEntity(
    this.id,
    this.game?.id,
    this.alphaChannel,
    this.animated,
    this.url,
    this.height,
    this.width,
    this.isCover
)