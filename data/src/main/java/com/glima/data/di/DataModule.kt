package com.glima.data.di

import com.glima.data.repository.GithubRepoRepositoryImpl
import com.glima.domain.repository.GithubRepoRepository
import org.koin.dsl.module

object DataModule {
    val dataModule = module {

        single<GithubRepoRepository> {
            GithubRepoRepositoryImpl(get())
        }
    }
}