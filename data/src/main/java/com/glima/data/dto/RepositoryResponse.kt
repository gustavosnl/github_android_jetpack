package com.glima.data.dto

import com.squareup.moshi.Json

data class RepositoryResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "forks_count") val forks: Int,
    @Json(name = "stargazers_count") val stars: Int,
    @Json(name = "description") val description: String?,
    @Json(name = "owner") val owner: OwnerResponse
)
