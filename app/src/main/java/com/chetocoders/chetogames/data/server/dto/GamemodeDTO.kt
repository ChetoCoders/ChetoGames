package com.chetocoders.chetogames.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GamemodeDTO (
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String?
) : Parcelable