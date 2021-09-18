package com.chetocoders.domain

enum class GameCategory {
    MAIN_GAME, DLC_ADDON, EXPANSION, BUNDLE, STANDALONE_EXPANSION, MOD, EPISODE, SEASON, REMAKE, REMASTER, EXPANDED_GAME, PORT, FORK;

    companion object {
        fun getValue(id: Int = -1) = GameCategory.values()[id]
        fun getIndex(gameCategory: GameCategory) = GameCategory.values().indexOf(gameCategory)
    }
}

