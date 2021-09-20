package com.chetocoders.domain

data class Image (
    val id: Long?,
    val game: Game?,
    val alphaChannel: Boolean?,
    val animated: Boolean?,
    val url: String?,
    val height: Int?,
    val width: Int?
)