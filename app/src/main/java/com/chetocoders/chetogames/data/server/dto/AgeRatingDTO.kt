package com.chetocoders.chetogames.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class AgeRatingDTOResult(
    val result: List<AgeRatingDTO>
)

@Parcelize
data class AgeRatingDTO (
    @SerializedName("id") val id: Long?,
    @SerializedName("category") val category: Int?,
    @SerializedName("rating") val rating: Int?
) : Parcelable