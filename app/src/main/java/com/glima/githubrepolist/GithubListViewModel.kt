package com.glima.githubrepolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.glima.domain.business.model.Repository
import com.glima.domain.business.usecase.ListGitReposUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GithubListViewModel(
    private val searchRepositoriesUseCase: ListGitReposUseCase
) : ViewModel() {

    private val INITIAL_SEARCH_TERM = "kotlin"
    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    private val _repositories = MutableLiveData<List<Repository>>()

    val repositories: LiveData<List<Repository>>
        get() = _repositories


    init {
        searchRepositories(INITIAL_SEARCH_TERM)
    }

    fun searchRepositories(searchTerm: String) {
        scope.launch {
            val searchResult = searchRepositoriesUseCase.executeAsync(searchTerm)
            _repositories.value = searchResult.repositories
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.complete()
    }
}