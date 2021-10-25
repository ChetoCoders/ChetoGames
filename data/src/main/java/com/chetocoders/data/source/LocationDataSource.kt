package com.chetocoders.data.source

interface LocationDataSource {
    suspend fun findLastRegion(): String?
}