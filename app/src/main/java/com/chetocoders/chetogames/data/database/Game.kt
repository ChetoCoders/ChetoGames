package com.chetocoders.chetogames.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chetocoders.domain.*
import java.time.LocalDateTime

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int,
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