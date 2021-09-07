package com.chetocoders.chetogames.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GenreDTOResult(
    val result: List<GenreDTO>
)

@Parcelize
data class GenreDTO (
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?
) : Parcelable