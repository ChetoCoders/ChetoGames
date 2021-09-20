package com.chetocoders.chetogames.data.server

object ServerConstants {
    const val URL: String = "https://api.igdb.com/v4"

    const val HEADER_CLIENT_ID = "Client-ID"
    const val HEADER_AUTHORIZATION = "Authorization"
    const val TYPE_TEXT_PLAIN = "text/plain"
}

object Queries {
    const val GET_GAMES =
        "fields id,age_ratings.category, age_ratings.rating,name,summary, release_dates.date, category, genres.name, platforms.name, game_modes.name,cover.alpha_channel,cover.animated,cover.game, cover.height,cover.image_id,cover.url,cover.width,screenshots.alpha_channel,screenshots.animated,screenshots.game, screenshots.height,screenshots.image_id,screenshots.url,screenshots.width; limit 500;"
}