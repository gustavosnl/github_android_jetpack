package com.glima.data.service

import com.glima.data.dto.RepositoriesSearchResultResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {

    @GET("search/repositories?")
    fun fetchRepositoriesByQuery(
        @Query("q") searchTerm: String,
        @Query("sortType") sortType: String,
        @Query("page") pageNumber: Int
    ): Deferred<RepositoriesSearchResultResponse>
}
