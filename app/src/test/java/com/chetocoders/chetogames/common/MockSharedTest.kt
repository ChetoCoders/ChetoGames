package com.chetocoders.chetogames.common

import com.chetocoders.domain.Game
import com.chetocoders.domain.GameCategory
import com.chetocoders.domain.GameDetail
import com.chetocoders.domain.Image

val mockedGameDetail =
    GameDetail(
        23635,
        "Dragon Atlas", null,
        null,
        GameCategory.MAIN_GAME,
        emptyList(),
        emptyList(),
        emptyList(),
        Image(
            31348,
            Game(
                23635,
                null,
                null,
                null,
                isExternal = false,
                isFavourite = false
            ),
            null,
            null,
            "//images.igdb.com/igdb/image/upload/t_thumb/kyquup8cobqhze6u9mum.jpg",
            374,
            666,
            isCover = false
        ),
        listOf(
            Image(
                31348,
                Game(
                    23635,
                    null,
                    null,
                    null,
                    isExternal = false,
                    isFavourite = false
                ),
                null,
                null,
                "//images.igdb.com/igdb/image/upload/t_thumb/kyquup8cobqhze6u9mum.jpg",
                374,
                666,
                isCover = false
            )
        ),
        emptyList(),
        isExternal = false,
        isFavourite = false
    )