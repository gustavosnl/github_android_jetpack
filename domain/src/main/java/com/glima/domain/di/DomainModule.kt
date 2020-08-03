package com.glima.domain.di

import com.glima.domain.business.usecase.ListGitReposUseCase
import com.glima.domain.business.usecase.ListGitReposUseCaseImpl
import org.koin.dsl.module

object DomainModule {

    val domainModule = module {

        single<ListGitReposUseCase> {
            ListGitReposUseCaseImpl(get())
        }

    }
}