package com.glima.domain.repository

import androidx.paging.PagingData
import com.glima.domain.business.model.Repository
import kotlinx.coroutines.flow.Flow

interface GithubRepoRepository {

    suspend fun searchRepositoriesByQuery(query: SearchParams): Flow<PagingData<Repository>>
}