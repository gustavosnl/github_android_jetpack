package com.glima.domain.business.usecase

import androidx.paging.PagingData
import com.glima.domain.business.model.Repository
import com.glima.domain.repository.GithubRepoRepository
import com.glima.domain.repository.SearchParams
import kotlinx.coroutines.flow.Flow

interface ListGitReposUseCase : UseCase.WithParams<SearchParams, PagingData<Repository>>

class ListGitReposUseCaseImpl(private val repository: GithubRepoRepository) : ListGitReposUseCase {

    override suspend fun execute(params: SearchParams): Flow<PagingData<Repository>> {
        return repository.searchRepositoriesByQuery(params)
    }
}