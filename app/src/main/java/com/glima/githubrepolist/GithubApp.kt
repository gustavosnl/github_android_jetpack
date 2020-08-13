package com.glima.githubrepolist

import android.app.Application
import com.glima.data.di.DataModule.dataModule
import com.glima.domain.di.DomainModule.domainModule
import com.glima.githubrepolist.BuildConfig.DEBUG
import com.glima.githubrepolist.di.AppModule.appModule
import com.glima.githubrepolist.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class GithubApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (DEBUG) Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@GithubApp)
            modules(
                listOf(
                    appModule,
                    presentationModule,
                    dataModule,
                    domainModule
                )
            )
        }
    }
}