package com.chetocoders.chetogames.data.database.dao

import androidx.room.*
import com.chetocoders.chetogames.data.database.entity.Platform
import com.chetocoders.chetogames.data.database.entity.PlatformGameRef


@Dao
interface PlatformDao {

    @Transaction
    @Query("SELECT * FROM Platform")
    fun getAll(): List<Platform>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(platform: Platform): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAssignedPlatform(platformGameRef: PlatformGameRef)
}