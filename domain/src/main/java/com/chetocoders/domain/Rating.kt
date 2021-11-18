package com.chetocoders.domain

sealed class Rating(val index: Long) {
    object THREE : Rating(1)
    object SEVEN : Rating(2)
    object TWELVE : Rating(3)
    object SIXTEEN : Rating(4)
    object EIGHTEEN : Rating(5)
    object RP : Rating(6)
    object EC : Rating(7)
    object E : Rating(8)
    object E10 : Rating(9)
    object T : Rating(10)
    object M : Rating(11)
    object D : Rating(12)

    companion object {
        fun get(index: Int?) = when (index) {
            1 -> THREE
            2 -> SEVEN
            3 -> TWELVE
            4 -> SIXTEEN
            5 -> EIGHTEEN
            6 -> RP
            7 -> EC
            8 -> E
            9 -> E10
            10 -> T
            11 -> M
            12 -> D
            else -> null
        }

        fun getValues() = arrayOf(THREE, SEVEN, TWELVE, SIXTEEN, EIGHTEEN, RP, EC, E, E10, T, M, D)
        fun getPEGIRatings() = arrayOf(THREE, SEVEN, TWELVE, SIXTEEN, EIGHTEEN)
        fun getESRBRatings() = arrayOf(RP, EC, E, E10, T, M, D)

    }

    override fun toString(): String = this.javaClass.simpleName
}

