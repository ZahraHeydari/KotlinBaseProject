package com.android.kotlinbaseproject.data.repository

import com.android.kotlinbaseproject.data.source.remote.APIService
import com.android.kotlinbaseproject.domain.model.Note
import kotlinx.coroutines.Deferred

class HomeRemoteRepository(val apiService: APIService) {

    suspend fun getNotes():List<Note>{
        return apiService.getNotes()
    }
}