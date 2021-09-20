package com.chetocoders.chetogames.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chetocoders.chetogames.data.database.dao.*
import com.chetocoders.chetogames.data.database.entity.*

@Database(
    entities = [Game::class,
        AgeRating::class,
        AgeRatingGameRef::class,
        GameMode::class,
        GameModeGameRef::class,
        Genre::class,
        GenreGameRef::class,
        Image::class,
        Platform::class,
        PlatformGameRef::class],
    version = 1
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class GameDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            GameDatabase::class.java,
            "game-db"
        ).build()
    }

    abstract fun ageRatingDao(): AgeRatingDao
    abstract fun ageRatingGameRefDao(): AgeRatingGameRefDao
    abstract fun gameDao(): GameDao
    abstract fun gameModeDao(): GameModeDao
    abstract fun gameModeGameRefDao(): GameModeGameRefDao
    abstract fun genreDao(): GenreDao
    abstract fun genreGameRefDao(): GenreGameRefDao
    abstract fun imageDao(): ImageDao
    abstract fun platformDao(): PlatformDao
    abstract fun platformGameRefDao(): PlatformGameRefDao
}