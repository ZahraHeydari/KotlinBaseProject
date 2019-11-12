package com.android.kotlinbaseproject.data.source.remote

import com.android.kotlinbaseproject.domain.model.Note
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface APIService {

    @GET("todos/")
    suspend fun getNotes(): List<Note>

}