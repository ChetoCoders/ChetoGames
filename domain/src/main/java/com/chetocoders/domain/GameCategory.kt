package com.chetocoders.domain

sealed class GameCategory(val index: Long) {
    object MAIN_GAME : GameCategory(0)
    object DLC_ADDON : GameCategory(1)
    object EXPANSION : GameCategory(2)
    object BUNDLE : GameCategory(3)
    object STANDALONE_EXPANSION : GameCategory(4)
    object MOD : GameCategory(5)
    object EPISODE : GameCategory(6)
    object SEASON : GameCategory(7)
    object REMAKE : GameCategory(8)
    object REMASTER : GameCategory(9)
    object EXPANDED_GAME : GameCategory(10)
    object PORT : GameCategory(11)
    object FORK : GameCategory(12)

    companion object {
        fun get(index: Int) = when (index) {
            0 -> MAIN_GAME
            1 -> DLC_ADDON
            2 -> EXPANSION
            3 -> BUNDLE
            4 -> STANDALONE_EXPANSION
            5 -> MOD
            6 -> EPISODE
            7 -> SEASON
            8 -> REMAKE
            9 -> REMASTER
            10 -> EXPANDED_GAME
            11 -> PORT
            12 -> FORK
            else -> null
        }

        fun getValues() = arrayOf(
            MAIN_GAME,
            DLC_ADDON,
            EXPANSION,
            BUNDLE,
            STANDALONE_EXPANSION,
            MOD,
            EPISODE,
            SEASON,
            REMAKE,
            REMASTER,
            EXPANDED_GAME,
            PORT,
            FORK
        )
    }

    override fun toString(): String = this.javaClass.simpleName
}

