package com.chetocoders.domain

sealed class AgeRatingCategory(val index: Long) {
    object ESRB: AgeRatingCategory(1)
    object PEGI: AgeRatingCategory(2)

    companion object {
        fun get(index: Int?) = when(index) {
            1 -> ESRB
            2 -> PEGI
            else -> null
        }
    }
}