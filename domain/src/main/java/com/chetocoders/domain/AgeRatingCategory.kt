package com.chetocoders.domain

enum class AgeRatingCategory {
    ESRB, PEGI;

    companion object {
        fun getValue(id: Int = -1) = values()[id + 1]
        fun getIndex(ageRatingCategory: AgeRatingCategory) = values().indexOf(ageRatingCategory) + 1
    }
}