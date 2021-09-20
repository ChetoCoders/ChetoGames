package com.chetocoders.domain

sealed class GameCategory(val index: Long) {
    object MAIN_GAME: GameCategory(1)
    object DLC_ADDON: GameCategory(2)
    object EXPANSION: GameCategory(3)
    object BUNDLE: GameCategory(4)
    object STANDALONE_EXPANSION: GameCategory(5)
    object MOD: GameCategory(6)
    object EPISODE: GameCategory(7)
    object SEASON: GameCategory(8)
    object REMAKE: GameCategory(9)
    object REMASTER: GameCategory(10)
    object EXPANDED_GAME: GameCategory(11)
    object PORT: GameCategory(12)
    object FORK: GameCategory(13)

    companion object {
        fun get(index: Int) = when(index) {
            1 -> MAIN_GAME
            2 -> DLC_ADDON
            3 -> EXPANSION
            4 -> BUNDLE
            5 -> STANDALONE_EXPANSION
            6 -> MOD
            7 -> EPISODE
            8 -> SEASON
            9 -> REMAKE
            10 -> REMASTER
            11 -> EXPANDED_GAME
            12 -> PORT
            13 -> FORK
            else -> null
        }
    }
}

