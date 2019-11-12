package com.android.kotlinbaseproject.di.module

import com.android.kotlinbaseproject.data.repository.HomeLocalRepository
import com.android.kotlinbaseproject.data.repository.HomeRemoteRepository
import com.android.kotlinbaseproject.data.source.local.AppDatabase
import com.android.kotlinbaseproject.data.source.remote.APIService
import com.android.kotlinbaseproject.data.source.remote.ApiErrorHandle
import com.android.kotlinbaseproject.domain.usecase.GetNotesUseCase
import com.android.kotlinbaseproject.presentation.note.NotesViewModel
import com.android.kotlinbaseproject.presentation.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val HomeModule = module {

    viewModel { HomeViewModel() }

    viewModel { NotesViewModel(get()) }

    single { createGetAlbumsUseCase(get(), createApiErrorHandle()) }

    single { createHomeLocalRepository(get()) }

    single { createHomeRemoteRepository(get()) }
}


fun createHomeLocalRepository(database: AppDatabase): HomeLocalRepository {
    return HomeLocalRepository(database)
}

fun createHomeRemoteRepository(apiService: APIService): HomeRemoteRepository {
    return HomeRemoteRepository(apiService)
}

fun createGetAlbumsUseCase(
    homeRemoteRepository: HomeRemoteRepository,
    apiErrorHandle: ApiErrorHandle
): GetNotesUseCase {
    return GetNotesUseCase(homeRemoteRepository, apiErrorHandle)
}