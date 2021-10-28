package com.chetocoders.usecases

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.repository.GenreRepository
import com.chetocoders.domain.Genre

class GetGenresUseCase(private val genreRepository: GenreRepository) {
    suspend fun invoke(): ResultData<List<Genre>> = genreRepository.getGenres()
}