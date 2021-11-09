package com.chetocoders.usecases

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.repository.AgeRatingRepository
import com.chetocoders.domain.AgeRating

class GetAgeRatingsUseCase (private val ageRatingsRepository: AgeRatingRepository) {
    suspend fun invoke(): ResultData<List<AgeRating>> = ageRatingsRepository.getAgeRatings()
}