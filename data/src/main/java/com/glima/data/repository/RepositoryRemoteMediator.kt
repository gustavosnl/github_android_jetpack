package com.glima.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.glima.data.database.GithubRepositoryDatabase
import com.glima.data.database.RemoteKeys
import com.glima.data.database.RepositoryEntity
import com.glima.data.mapper.asEntity
import com.glima.data.service.RepositoryService
import com.glima.domain.repository.SearchParams
import timber.log.Timber
import java.io.InvalidObjectException

private const val STARTING_PAGE = 1

@ExperimentalPagingApi
class RepositoryRemoteMediator(
    private val searchParams: SearchParams,
    private val database: GithubRepositoryDatabase,
    private val service: RepositoryService
) : RemoteMediator<Int, RepositoryEntity>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, RepositoryEntity>)
            : MediatorResult {

        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    Timber.d("REFRESH called")
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextIndex?.minus(1) ?: STARTING_PAGE
                }
                LoadType.PREPEND -> {
                    Timber.d("PREPEND called")
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                        ?: throw InvalidObjectException("Remote key and the prevKey should not be null")
                    remoteKeys.previousIndex
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    remoteKeys.previousIndex
                }
                LoadType.APPEND -> {
                    Timber.d("APPEND called")
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    if (remoteKeys?.nextIndex == null) {
                        throw InvalidObjectException("Remote key should not be null for $loadType")
                    }
                    remoteKeys.nextIndex
                }
            }
            Timber.d("LoadKey: $loadKey ")

            val response = service.fetchRepositoriesByQuery(
                searchTerm = searchParams.query,
                sortType = searchParams.sort.sort,
                itemsPerPage = state.config.pageSize,
                pageNumber = loadKey
            )

            val repositories = response.repositories
            val isLastPage = repositories.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.clear()
                }
                val previousKey = if (loadKey == STARTING_PAGE) null else loadKey - 1
                val nextKey = if (isLastPage) null else loadKey + 1
                val remoteKeys = repositories.map {
                    RemoteKeys(
                        repositoryId = it.id,
                        nextIndex = nextKey,
                        previousIndex = previousKey
                    )
                }
                database.repositoryDAO.insertAll(repositories.map {
                    it.asEntity()
                })
                database.remoteKeysDAO.insertAll(remoteKeys)
            }
            MediatorResult.Success(endOfPaginationReached = isLastPage)

        } catch (e: Exception) {
            Timber.e(e)
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, RepositoryEntity>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                database.remoteKeysDAO.findKeysByRepositoryId(repo.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, RepositoryEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                database.remoteKeysDAO.findKeysByRepositoryId(repo.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, RepositoryEntity>):
            RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                database.remoteKeysDAO.findKeysByRepositoryId(repoId)
            }
        }
    }
}