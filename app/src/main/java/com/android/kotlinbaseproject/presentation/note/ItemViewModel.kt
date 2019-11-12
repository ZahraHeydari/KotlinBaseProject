package com.android.kotlinbaseproject.presentation.note

import androidx.lifecycle.MutableLiveData
import com.android.kotlinbaseproject.domain.model.Note


/**A helper class for the UI controller that is responsible for
 * preparing data for [ItemViewModel] as the UI
 *
 * @CREATOR ZARA
 * */
class ItemViewModel(val album: Note) {

    private val TAG = ItemViewModel::class.java.name
    val albumData = MutableLiveData<Note>()

    init {
        albumData.value = album
    }

}