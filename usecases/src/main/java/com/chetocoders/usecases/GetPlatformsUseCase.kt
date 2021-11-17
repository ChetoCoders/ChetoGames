package com.chetocoders.usecases

import com.chetocoders.data.common.ResultData
import com.chetocoders.data.repository.PlatformsRepository
import com.chetocoders.domain.Platform

class GetPlatformsUseCase(private val platformsRepository: PlatformsRepository) {
    suspend fun invoke(): ResultData<List<Platform>> = platformsRepository.getPlatforms()
}