package com.chetocoders.chetogames.data.server

object ApiQueryConstants {

    const val GAME_DETAILS_QUERY =
        "fields id,age_ratings.category, age_ratings.rating,name,summary, release_dates.date," +
                " category, genres.name, platforms.name, game_modes.name,cover.alpha_channel,cover.animated," +
                "cover.game, cover.height,cover.image_id,cover.url,cover.width,screenshots.alpha_channel," +
                "screenshots.animated,screenshots.game, screenshots.height,screenshots.image_id," +
                "screenshots.url,screenshots.width;"

}