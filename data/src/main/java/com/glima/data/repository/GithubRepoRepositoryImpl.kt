package com.glima.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.glima.data.mapper.asDomain
import com.glima.data.service.RepositoryService
import com.glima.domain.business.model.Repository
import com.glima.domain.repository.GithubRepoRepository
import com.glima.domain.repository.SearchParams
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class GithubRepoRepositoryImpl(private val service: RepositoryService) :
    GithubRepoRepository {
    override suspend fun searchRepositoriesByQuery(query: SearchParams): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { RepositoryPagingSource(service, query) }
        ).flow
    }
}

private const val STARTING_PAGE = 1

class RepositoryPagingSource(
    private val service: RepositoryService,
    private val searchParams: SearchParams
) : PagingSource<Int, Repository>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val position = params.key ?: STARTING_PAGE
        Timber.d("position: $position")
        return try {
            val result =
                service.fetchRepositoriesByQuery(
                    searchParams.query,
                    searchParams.sort.sort,
                    position,
                    params.loadSize
                )

            LoadResult.Page(
                data = result.asDomain().repositories,
                prevKey = if (position == STARTING_PAGE) null else position - 1,
                nextKey = if (result.repositories.isEmpty()) null else position + 1
            )

        } catch (e: Exception) {
            Timber.e("error: ${e.message}")
            LoadResult.Error(e)
        }
    }
}

