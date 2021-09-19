package com.chetocoders.chetogames.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chetocoders.chetogames.data.database.dao.*
import com.chetocoders.chetogames.data.database.entity.Game

@Database(entities = [Game::class], version = 1)
@TypeConverters(Converters::class)
abstract class GameDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            GameDatabase::class.java,
            "game-db"
        ).build()
    }

    abstract fun ageRatingDao(): AgeRatingDao
    abstract fun gameDao(): GameDao
    abstract fun gameModeDao(): GamemodeDao
    abstract fun genreDao(): GenreDao
    abstract fun imageDao(): ImageDao
    abstract fun platformDao(): PlatformDao
}