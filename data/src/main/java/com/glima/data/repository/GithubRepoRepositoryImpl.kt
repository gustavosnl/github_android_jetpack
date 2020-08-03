package com.glima.data.repository

import com.glima.data.mapper.asDomain
import com.glima.data.service.RepositoryService
import com.glima.domain.business.model.RepositorySearchResult
import com.glima.domain.repository.GithubRepoRepository

class GithubRepoRepositoryImpl(private val service: RepositoryService) : GithubRepoRepository {
    override suspend fun searchRepositoriesByQuery(query: String): RepositorySearchResult {
        val result = service.fetchRepositoriesByQuery(query, "asc", 1).await()
        return result.asDomain()
    }
}
