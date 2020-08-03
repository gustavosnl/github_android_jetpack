package com.glima.domain.business.model

data class RepositorySearchResult(
    val totalResults: Int,
    val repositories: List<Repository>
)