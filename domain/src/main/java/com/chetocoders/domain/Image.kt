package com.chetocoders.domain

data class Image (
    var id: Long?,
    var game: Game? = null,
    var alphaChannel: Boolean?,
    var animated: Boolean?,
    var url: String?,
    var height: Int?,
    var width: Int?,
    var isCover: Boolean?
)