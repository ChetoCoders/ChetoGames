package com.chetocoders.chetogames.data.server.mapper

import com.chetocoders.chetogames.data.server.dto.ScreenshotDTO
import com.chetocoders.domain.Game
import com.chetocoders.domain.Image

fun ScreenshotDTO.toDomain() = Image(
    this.id,
    Game(this.game),
    this.alphaChannel,
    this.animated,
    this.url,
    this.height,
    this.width,
    false
)

fun Image.toScreenshotDTO() = ScreenshotDTO(
    this.id,
    this.alphaChannel,
    this.animated,
    null,
    this.height,
    null,
    this.url,
    this.width
)