package com.glima.domain.business.usecase

import kotlinx.coroutines.flow.Flow

object UseCase {

    interface WithParams<in P, R> {
        suspend fun execute(params: P): Flow<R>
    }
}