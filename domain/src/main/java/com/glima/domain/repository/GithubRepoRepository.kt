package com.glima.domain.repository

import com.glima.domain.business.model.RepositorySearchResult

interface GithubRepoRepository {

    suspend fun searchRepositoriesByQuery(query: String): RepositorySearchResult
}