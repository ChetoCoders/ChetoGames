package com.chetocoders.chetogames.data.database.entity

import androidx.room.*
import java.time.LocalDateTime

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true) val gameId: Long,
    val title: String?,
    val description: String?,
    val released: LocalDateTime?,
    val isExternal: Boolean?,
    val isFavourite: Boolean?
)

data class GameParentEntity (
    @Embedded val game: Game,
    @Relation(parentColumn = "gameId", entityColumn = "ageRatingId", associateBy = Junction(AgeRatingGameRef::class)) val ageRatings: List<AgeRating>?,
    @Relation(parentColumn = "gameId", entityColumn = "gameModeId", associateBy = Junction(GameModeGameRef::class)) val gameModes: List<GameMode>?,
    @Relation(parentColumn = "gameId", entityColumn = "genreId", associateBy = Junction(GenreGameRef::class)) val genres: List<Genre>?,
    @Relation(parentColumn = "gameId", entityColumn = "platformId", associateBy = Junction(PlatformGameRef::class)) val platforms: List<Platform>?,

    @Relation(entity = Image::class, parentColumn = "gameId", entityColumn = "gameId") val cover: Image?,
    @Relation(entity = Image::class, parentColumn = "gameId", entityColumn = "gameId") val screenshots: List<Image>?,
)