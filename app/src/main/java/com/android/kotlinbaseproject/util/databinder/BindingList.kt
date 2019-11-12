package com.android.kotlinbaseproject.util.databinder

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.kotlinbaseproject.domain.model.Note
import com.android.kotlinbaseproject.presentation.note.NotesAdapter

class BindingList {

    companion object {

        @JvmStatic
        @BindingAdapter("app:setup_adapter")
        fun setItems(recyclerView: RecyclerView, items: List<Note>?) {
            val adapter = recyclerView.adapter as NotesAdapter?
            items?.let {
                adapter?.notes = it
            }
        }
    }
}
