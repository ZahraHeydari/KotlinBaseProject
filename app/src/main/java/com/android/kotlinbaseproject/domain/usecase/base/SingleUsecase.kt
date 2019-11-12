package com.android.kotlinbaseproject.domain.usecase.base

import com.android.kotlinbaseproject.data.source.remote.ApiErrorHandle
import kotlinx.coroutines.*
import retrofit2.HttpException

abstract class SingleUseCase<Type, in Params>(private val apiErrorHandle: ApiErrorHandle?) where Type : Any {

    val TAG = SingleUseCase::class.java.name
    abstract suspend fun run(params: Params? = null): Type

    @ExperimentalCoroutinesApi
    fun invoke(
        scope: CoroutineScope,
        params: Params?,
        onResult: (UseCaseResponse<Type>)
    ) {
        val backgroundJob = scope.async { run(params) }
        scope.launch {
            backgroundJob.await().let {
                try {
                    onResult.onSuccess(it)
                } catch (e: HttpException) {
                    onResult.onError(apiErrorHandle?.traceErrorException(e))
                }
            }
        }
    }
}