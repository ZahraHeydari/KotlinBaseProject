package com.android.kotlinbaseproject.presentation.note

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.kotlinbaseproject.data.source.remote.ErrorModel
import com.android.kotlinbaseproject.domain.model.Note
import com.android.kotlinbaseproject.domain.usecase.GetNotesUseCase
import com.android.kotlinbaseproject.domain.usecase.base.UseCaseResponse
import com.android.kotlinbaseproject.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class NotesViewModel constructor(private val getAlbumsUseCase: GetNotesUseCase) : BaseViewModel() {

    val TAG = NotesViewModel::class.java.name
    val empty = MutableLiveData<Boolean>()
    val mSnackbarText = MutableLiveData<String>()
    val albumsReceivedLiveData = MutableLiveData<List<Note>>()
    val isDataLoaded = MutableLiveData<Boolean>()

    @ExperimentalCoroutinesApi
    fun loadAlbums() {
        getAlbumsUseCase.invoke(scope, null, object : UseCaseResponse<List<Note>> {
            override fun onSuccess(result: List<Note>) {
                Log.i(TAG, "result: $result")
                empty.value = result.isEmpty()
                isDataLoaded.value = true
                albumsReceivedLiveData.value = result
            }

            override fun onError(errorModel: ErrorModel?) {
                isDataLoaded.postValue(false)
                mSnackbarText.postValue(errorModel?.message)
            }

        })
    }


}