package com.glima.domain.repository

import com.glima.domain.business.model.RepositorySearchResult
import kotlinx.coroutines.Deferred

interface GithubRepoRepository {

    suspend fun searchRepositoriesByQuery(query: String): RepositorySearchResult
}