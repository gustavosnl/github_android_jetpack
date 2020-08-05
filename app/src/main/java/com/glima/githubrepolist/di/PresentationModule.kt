package com.glima.githubrepolist.di

import com.glima.githubrepolist.GithubListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        GithubListViewModel(get())
    }
}