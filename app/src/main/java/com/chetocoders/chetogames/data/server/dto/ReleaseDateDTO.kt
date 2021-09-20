package com.chetocoders.chetogames.data.server.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReleaseDateDTO (
    val id: Int,
    val date: Long
) : Parcelable