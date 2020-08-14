package com.glima.data.di

import androidx.room.Room
import com.glima.data.GithubRepositoryDatabase
import com.glima.data.dao.RepositoryDAO
import com.glima.data.repository.GithubRepoRepositoryImpl
import com.glima.domain.repository.GithubRepoRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DataModule {
    val dataModule = module {
        single<RepositoryDAO> {
            (get() as GithubRepositoryDatabase).repositoryDAO
        }

        single<GithubRepositoryDatabase> {
            synchronized(this) {
                Room.databaseBuilder(
                    androidContext().applicationContext,
                    GithubRepositoryDatabase::class.java,
                    "repository_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }

        single<GithubRepoRepository> {
            GithubRepoRepositoryImpl(get())
        }
    }
}