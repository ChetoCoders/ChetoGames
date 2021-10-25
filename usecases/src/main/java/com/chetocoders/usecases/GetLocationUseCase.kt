package com.chetocoders.usecases

import com.chetocoders.data.repository.RegionRepository

class GetRegionUseCase(private val regionRepository: RegionRepository) {
    suspend fun invoke(): String = regionRepository.findLastRegion()
}