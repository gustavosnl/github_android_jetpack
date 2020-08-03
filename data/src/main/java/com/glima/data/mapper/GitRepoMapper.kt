package com.glima.data.mapper

import com.glima.data.dto.OwnerResponse
import com.glima.data.dto.RepositoriesSearchResultResponse
import com.glima.data.dto.RepositoryResponse
import com.glima.domain.business.model.Owner
import com.glima.domain.business.model.Repository
import com.glima.domain.business.model.RepositorySearchResult

fun OwnerResponse.asDomain() = Owner(
    name = this.name,
    avatar = this.avatarUrl
)

fun RepositoryResponse.asDomain() = Repository(
    id = this.id,
    name = this.name,
    description = this.description,
    forks = this.forks,
    owner = this.owner.asDomain(),
    stars = this.stars
)

fun RepositoriesSearchResultResponse.asDomain() = RepositorySearchResult(
    totalResults = this.totalResults,
    repositories = this.repositories?.map {
        it.asDomain()
    }
)