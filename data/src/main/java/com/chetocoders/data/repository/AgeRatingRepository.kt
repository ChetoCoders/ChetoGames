package com.chetocoders.data.repository

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.domain.AgeRating

class AgeRatingRepository(private val localDataSource: LocalDataSource) {

    suspend fun getAgeRatings(): ResultData<List<AgeRating>> {
        return try {
            ResultData.success(localDataSource.getAgeRatings())
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }
}