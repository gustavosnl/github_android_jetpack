package com.glima.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class RepositoriesSearchResultResponse(
    @Json(name = "total_count") val totalResults: Int,
    @Json(name = "items") val repositories: List<RepositoryResponse>
)