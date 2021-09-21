package com.chetocoders.chetogames.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.chetocoders.chetogames.data.database.entity.Platform
import com.chetocoders.chetogames.data.database.entity.PlatformGameRef


@Dao
interface PlatformDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(platform: Platform): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAssignedPlatform( platformGameRef: PlatformGameRef)
}