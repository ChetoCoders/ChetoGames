package com.chetocoders.data.repository

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.domain.Genre

class GenreRepository(private val localDataSource: LocalDataSource) {
    suspend fun getGenres(): ResultData<List<Genre>> {
        return try {
            ResultData.success(localDataSource.getGenres())
        } catch (exception: Exception) {
            ResultData.failure(exception)
        }
    }
}