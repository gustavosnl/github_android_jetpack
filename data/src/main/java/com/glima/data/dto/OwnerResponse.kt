package com.glima.data.dto

import com.squareup.moshi.Json

data class OwnerResponse(
    @Json(name = "login") val name: String,
    @Json(name = "avatar_url") val avatarUrl: String
)