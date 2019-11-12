package com.android.kotlinbaseproject.domain.usecase.base

import com.android.kotlinbaseproject.data.source.remote.ErrorModel

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel?)
}
