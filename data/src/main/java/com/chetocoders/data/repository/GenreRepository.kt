package com.chetocoders.data.repository

import com.chetocoders.data.source.LocalDataSource
import com.chetocoders.domain.Genre

class GenreRepository(private val localDataSource: LocalDataSource) {
    suspend fun getGenres(): List<Genre> = localDataSource.getGenres()
}