package com.chetocoders.chetogames.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.chetocoders.chetogames.data.database.entity.Genre
import com.chetocoders.chetogames.data.database.entity.GenreGameRef


@Dao
interface GenreDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(genre: Genre): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAssignedGenre( genreGameRef: GenreGameRef )
}
