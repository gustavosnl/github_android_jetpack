package com.glima.domain.business.usecase

import com.glima.domain.business.model.RepositorySearchResult
import com.glima.domain.repository.GithubRepoRepository

interface ListGitReposUseCase : Usecase.WithParams<String, RepositorySearchResult>

class ListGitReposUseCaseImpl(private val repository: GithubRepoRepository) : ListGitReposUseCase {
    override suspend fun executeAsync(params: String): RepositorySearchResult {
        return repository.searchRepositoriesByQuery(params)
    }
}