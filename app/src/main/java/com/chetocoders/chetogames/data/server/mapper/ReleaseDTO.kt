package com.chetocoders.chetogames.data.server.mapper

import com.chetocoders.chetogames.data.server.dto.ReleaseDateDTO
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

fun ReleaseDateDTO.toDomain() = LocalDateTime.ofInstant(Instant.ofEpochMilli(this.date),
    TimeZone.getDefault().toZoneId())

