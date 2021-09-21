package com.chetocoders.chetogames.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScreenshotDTO (
    @SerializedName("id") val id: Long?,
    @SerializedName("alpha_channel") val alphaChannel: Boolean?,
    @SerializedName("animated") val animated: Boolean?,
    @SerializedName("game") val game: Long?,
    @SerializedName("height") val height: Int?,
    @SerializedName("image_id") val imageId: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("width") val width: Int?
) : Parcelable