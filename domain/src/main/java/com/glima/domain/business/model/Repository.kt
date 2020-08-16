package com.glima.domain.business.model

data class Repository(
    val id: Long,
    val name: String,
    val forks: Int,
    val stars: Int,
    val description: String?,
    val owner: Owner
)