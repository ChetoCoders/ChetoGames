package com.chetocoders.domain

data class Image (
    val id: Int?,
    val game: Game?,
    val alphaChannel: Boolean?,
    val animated: Boolean?,
    val url: String?,
    val height: Int?,
    val width: Int?
)