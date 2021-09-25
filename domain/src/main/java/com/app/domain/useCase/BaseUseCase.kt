package com.app.domain.useCase

interface BaseUseCase<T, in Params> {

    suspend operator fun invoke(params: Params): T
}