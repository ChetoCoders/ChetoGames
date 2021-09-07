package com.chetocoders.chetogames.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GameDTOResult(
    val result: List<GameDTO>
)

@Parcelize
data class GameDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("summary") val summary: String?,
    @SerializedName("release_dates") val releaseDates: List<Int?>?,
    @SerializedName("category") val category: Int?,
    @SerializedName("genres") val genres: List<Int?>?,
    @SerializedName("platforms") val platforms: List<Int?>?,
    @SerializedName("game_modes") val gameModes: List<Int?>?,
    @SerializedName("cover") val cover: Int??,
    @SerializedName("screenshots") val screenshots: List<Int?>?,
    @SerializedName("age_ratings") val ageRatings: List<Int?>?
) : Parcelable
