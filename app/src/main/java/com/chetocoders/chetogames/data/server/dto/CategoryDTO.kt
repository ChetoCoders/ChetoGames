package com.chetocoders.chetogames.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CategoryDTOResult(
    val result: List<CategoryDTO>
)

@Parcelize
data class CategoryDTO (
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?
) : Parcelable