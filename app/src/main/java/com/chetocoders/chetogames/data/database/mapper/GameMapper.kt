package com.chetocoders.chetogames.data.database.mapper

import com.chetocoders.domain.GameCategory
import com.chetocoders.chetogames.data.database.entity.Game as GameEntity
import com.chetocoders.chetogames.data.database.entity.GameDetail as GameDetailEntity
import com.chetocoders.chetogames.data.database.entity.GameSimple as GameSimpleEntity
import com.chetocoders.domain.Game as GameDomain
import com.chetocoders.domain.GameDetail as GameDetailDomain

fun GameSimpleEntity.toDomain() = GameDomain(
    this.game.gameId,
    this.game.title,
    this.platforms?.map { it.toDomain() },
    this.cover?.toDomain(),
    this.game.isExternal,
    this.game.isFavourite
)

fun GameDetailEntity.toDomain() = GameDetailDomain(
    this.game.gameId,
    this.game.title,
    this.game.description,
    this.game.released,
    this.game.category?.let { GameCategory.get(it.toInt()) },
    this.genres?.map { it.toDomain() },
    this.platforms?.map { it.toDomain() },
    this.gameModes?.map { it.toDomain() },
    this.cover?.toDomain(),
    this.screenshots?.map { it.toDomain() },
    this.ageRatings?.map { it.toDomain() },
    this.game.isExternal,
    this.game.isFavourite
)

fun GameDomain.toEntity() = GameSimpleEntity(
    GameEntity(
        this.id,
        this.title,
        null,
        null,
        null,
        this.isExternal,
        this.isFavourite
    ),
    this.platforms?.map { it.toEntity() },
    this.cover?.toEntity()
)

fun GameDetailDomain.toEntity() = GameDetailEntity(
    GameEntity(
        this.id,
        this.title,
        this.description,
        this.released,
        this.category?.index,
        this.isExternal,
        this.isFavourite
    ),
    this.ageRatings?.map { it.toEntity() },
    this.gameModes?.map { it.toEntity() },
    this.genres?.map { it.toEntity() },
    this.platforms?.map { it.toEntity() },
    this.cover?.toEntity(),
    this.screenshots?.map { it.toEntity() }
)