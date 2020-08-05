package com.glima.githubrepolist.di

import com.glima.data.service.RepositoryService
import com.glima.githubrepolist.BuildConfig
import com.glima.githubrepolist.BuildConfig.DEBUG
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AppModule {

    val appModule = module {
        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(get())
                .build()
        }

        single {
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        single {
            val interceptor = HttpLoggingInterceptor().apply {
                level = if (DEBUG) BODY else NONE
            }

            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor { chain: Interceptor.Chain ->
                    val request = chain.request().newBuilder()
                        .header("Accept", "application/vnd.github.v3+json")
                        .build()
                    chain.proceed(request)
                }
                .build()
        }

        single<RepositoryService> {
            (get() as Retrofit).create(RepositoryService::class.java)
        }
    }
}