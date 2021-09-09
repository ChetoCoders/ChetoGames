package com.chetocoders.domain

enum class Rating {
    THREE, SEVEN, TWELVE, SIXTEEN, EIGHTEEN, RP, EC, E, E10, T, M, D;

    companion object {
        fun getValue(id: Int = -1) = Rating.values()[id + 1]
        fun getIndex(rating: Rating) = Rating.values().indexOf(rating) + 1
    }
}
