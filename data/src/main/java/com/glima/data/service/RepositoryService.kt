package com.glima.data.service

import com.glima.data.dto.RepositoriesSearchResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {

    @GET("search/repositories?")
    suspend fun fetchRepositoriesByQuery(
        @Query("q") searchTerm: String,
        @Query("sortType") sortType: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepositoriesSearchResultResponse
}
