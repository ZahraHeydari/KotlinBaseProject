package com.android.kotlinbaseproject.domain.usecase

import com.android.kotlinbaseproject.data.repository.HomeRemoteRepository
import com.android.kotlinbaseproject.data.source.remote.ApiErrorHandle
import com.android.kotlinbaseproject.domain.model.Note
import com.android.kotlinbaseproject.domain.usecase.base.SingleUseCase
import kotlinx.coroutines.Deferred

/**
 * An interactor that calls the actual implementation of [NotesViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetNotesUseCase(
    private val homeRemoteRepository: HomeRemoteRepository,
    apiErrorHandle: ApiErrorHandle
) : SingleUseCase<List<Note>, Any?>(apiErrorHandle) {

    override suspend fun run(params: Any?): List<Note> {
        return homeRemoteRepository.getNotes()
    }
}