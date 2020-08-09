package com.glima.domain.repository

enum class SortType(val sort: String) {
    TOP_RATED("stars")
}


data class SearchParams(
    val query: String,
    val sort: SortType,
    var page: Int = 1
)

