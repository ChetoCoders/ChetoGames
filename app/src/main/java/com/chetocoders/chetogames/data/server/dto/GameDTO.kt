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
    @SerializedName("release_dates") val releaseDates: List<ReleaseDateDTO>?,
    @SerializedName("category") val category: Int?,
    @SerializedName("genres") val genres: List<GenreDTO?>?,
    @SerializedName("platforms") val platforms: List<PlatformDTO>?,
    @SerializedName("game_modes") val gameModes: List<GamemodeDTO>?,
    @SerializedName("cover") val cover: CoverDTO?,
    @SerializedName("screenshots") val screenshots: List<ScreenshotDTO>?,
    @SerializedName("age_ratings") val ageRatings: List<AgeRatingDTO>?
) : Parcelable