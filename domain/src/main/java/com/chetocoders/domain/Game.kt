package com.chetocoders.domain

import java.time.LocalDateTime

data class Game (
    var id: Long?,
    var title: String? = null,
    var platforms: List<Platform>? = null,
    var cover: Image? = null,
    var isExternal: Boolean = false,
    var isFavourite: Boolean = false
)

data class GameDetail (
    var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var released: LocalDateTime? = null,
    var category: GameCategory? = null,
    var genres: List<Genre>? = null,
    var platforms: List<Platform>? = null,
    var gameModes: List<GameMode>? = null,
    var cover: Image? = null,
    var screenshots: List<Image>? = null,
    var ageRatings: List<AgeRating>? = null,
    var isExternal: Boolean = false,
    var isFavourite: Boolean = false
)