package com.chetocoders.chetogames.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PlatformDTOResult(
    val result: List<PlatformDTO>
)

@Parcelize
data class PlatformDTO (
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?
) : Parcelable