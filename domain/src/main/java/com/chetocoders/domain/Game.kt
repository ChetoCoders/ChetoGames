package com.chetocoders.domain

import java.time.LocalDateTime

data class Game (
    val id: Int?,
    val title: String? = null,
    val platforms: List<Platform>? = null,
    val cover: Image? = null,
    val isExternal: Boolean = false,
    val isFavourite: Boolean = false
)

data class GameDetail (
    val id: Int?,
    val title: String?,
    val description: String?,
    val released: LocalDateTime?,
    val category: GameCategory?,
    val genres: List<Genre>?,
    val platforms: List<Platform>?,
    val gameModes: List<Gamemode>?,
    val cover: Image?,
    val screenshots: List<Image>?,
    val ageRatings: List<AgeRating>?,
    val isExternal: Boolean?,
    val isFavourite: Boolean?
)