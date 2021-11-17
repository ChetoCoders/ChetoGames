package com.chetocoders.chetogames.data.database.dao

import androidx.room.*
import com.chetocoders.chetogames.data.database.entity.Genre
import com.chetocoders.chetogames.data.database.entity.GenreGameRef


@Dao
interface GenreDao {

    @Transaction
    @Query("SELECT * FROM Genre")
    fun getAll(): List<Genre>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(genre: Genre): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAssignedGenre(genreGameRef: GenreGameRef)
}
