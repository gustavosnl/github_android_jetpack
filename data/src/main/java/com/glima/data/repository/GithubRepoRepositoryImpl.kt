package com.glima.data.repository

import androidx.paging.*
import com.glima.data.database.GithubRepositoryDatabase
import com.glima.data.mapper.asDomain
import com.glima.data.service.RepositoryService
import com.glima.domain.business.model.Repository
import com.glima.domain.repository.GithubRepoRepository
import com.glima.domain.repository.SearchParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GithubRepoRepositoryImpl(
    private val service: RepositoryService,
    private val database: GithubRepositoryDatabase
) : GithubRepoRepository {

    @ExperimentalPagingApi
    override suspend fun searchRepositoriesByQuery(query: SearchParams): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(pageSize = 50),
            remoteMediator = RepositoryRemoteMediator(query, database, service),
            pagingSourceFactory = { database.repositoryDAO.getAll() }
        ).flow.map {
            it.map { repo ->
                repo.asDomain()
            }
        }

    }
}
