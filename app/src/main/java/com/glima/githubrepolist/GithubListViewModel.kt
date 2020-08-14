package com.glima.githubrepolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.glima.domain.business.model.Repository
import com.glima.domain.business.usecase.ListGitReposUseCase
import com.glima.domain.repository.SearchParams
import com.glima.domain.repository.SortType
import kotlinx.coroutines.flow.Flow

class GithubListViewModel(
    private val searchRepositoriesUseCase: ListGitReposUseCase
) : ViewModel() {
    private var currentSearchResult: Flow<PagingData<Repository>>? = null

    suspend fun searchRepositories(searchTerm: String): Flow<PagingData<Repository>> {
        val newResult = searchRepositoriesUseCase.execute(
            SearchParams(
                query = searchTerm,
                sort = SortType.TOP_RATED
            )
        ).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    suspend fun retry() {
        searchRepositories("kotlin")
    }

}


