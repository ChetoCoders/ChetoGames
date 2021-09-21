package com.chetocoders.chetogames.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.chetocoders.chetogames.data.database.entity.Image


@Dao
interface ImageDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: Image): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(imageList: List<Image>): List<Long>
}