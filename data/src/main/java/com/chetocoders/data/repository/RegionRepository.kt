package com.chetocoders.data.repository

import com.chetocoders.data.source.LocationDataSource

class RegionRepository(
    private val locationDataSource: LocationDataSource,
    private val permissionChecker: PermissionChecker
) {
    companion object {
        private const val DEFAULT_REGION = "ES"
    }

    suspend fun findLastRegion(): String {

        if (permissionChecker.check(PermissionChecker.Permission.COARSE_LOCATION)) {
            return  locationDataSource.findLastRegion() ?: DEFAULT_REGION
        } else {
            return  DEFAULT_REGION
        }
    }
}

interface PermissionChecker {

    enum class Permission { COARSE_LOCATION }

    suspend fun check(permission: Permission): Boolean
}