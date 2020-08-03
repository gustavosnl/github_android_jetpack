package com.glima.githubrepolist

import androidx.lifecycle.ViewModel
import com.glima.domain.repository.GithubRepoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.get

class GithubListViewModel : ViewModel(), KoinComponent {
    val repository: GithubRepoRepository = get()
    private val job = Job()

    private val scope = CoroutineScope(job + Dispatchers.Main)

    init {
        fetchRepositories()
    }

    private fun fetchRepositories() {
        scope.launch {
            repository.searchRepositoriesByQuery("kotlin")
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.complete()
    }
}