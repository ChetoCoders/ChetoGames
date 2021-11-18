package com.chetocoders.chetogames.data.database.dao

import androidx.room.*
import com.chetocoders.chetogames.data.database.entity.AgeRating
import com.chetocoders.chetogames.data.database.entity.AgeRatingGameRef

@Dao
interface AgeRatingDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ageRating: AgeRating): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAssignedAgeRating(ageRatingGameRef: AgeRatingGameRef)
}

