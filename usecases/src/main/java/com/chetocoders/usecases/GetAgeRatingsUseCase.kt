package com.chetocoders.usecases

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.repository.AgeRatingRepository
import com.chetocoders.domain.AgeRating

class GetAgeRatingsByCategoryUseCase (private val ageRatingsRepository: AgeRatingRepository) {
    suspend fun invoke(index: Int): ResultData<List<AgeRating>> = ageRatingsRepository.getAgeRatingsByCategory(index)
}