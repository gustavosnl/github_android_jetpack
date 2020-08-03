package com.glima.domain.business.usecase

object Usecase {

    interface WithParams<in P, R> {
        suspend fun executeAsync(params: P): R
    }

}