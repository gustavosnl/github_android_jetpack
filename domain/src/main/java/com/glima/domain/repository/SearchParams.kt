package com.glima.data.domain

enum class SortType(val sort: String) {
    TOP_RATED("stars")
}


data class SearchParams(
    val query: String,
    val sort: SortType,
    var page: Int = 1
) {
    fun nextPage(): SearchParams {
        ++page
        return this
    }

    fun previousPage(): SearchParams {
        --page
        return this
    }
}
