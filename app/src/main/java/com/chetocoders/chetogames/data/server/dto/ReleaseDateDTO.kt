package com.chetocoders.chetogames.data.server.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ReleaseDateDTOResult(
    val result: List<ReleaseDateDTO>
)

@Parcelize
data class ReleaseDateDTO (
    val id: Long,
    val date: Long
) : Parcelable