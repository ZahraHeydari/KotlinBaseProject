package com.android.kotlinbaseproject.presentation.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.kotlinbaseproject.R
import com.android.kotlinbaseproject.databinding.HolderNoteBinding
import com.android.kotlinbaseproject.domain.model.Note
import com.android.kotlinbaseproject.presentation.note.NotesAdapter.NoteViewHolder
import java.util.*
import kotlin.properties.Delegates

/**
 * This class is responsible for converting each data entry [Note]
 * into [NoteViewHolder] that can then be added to the AdapterView.
 *
 * Created by ZARA
 */
internal class NotesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = NotesAdapter::class.java.name
    var notes: List<Note> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    /**
     * This method is called right when adapter is created &
     * is used to initialize ViewHolders
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderNoteBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_note, parent, false
        )
        return NoteViewHolder(holderNoteBinding)
    }

    /** It is called for each ViewHolder to bind it to the adapter &
     * This is where we pass data to ViewHolder
     * */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NoteViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Note {
        return notes[position]
    }

    /**
     * This method returns the size of collection that contains the items we want to display
     * */
    override fun getItemCount(): Int {
        return notes.size
    }

    inner class NoteViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {


        fun onBind(note: Note) {
            val holderNoteBinding = dataBinding as HolderNoteBinding
            holderNoteBinding.itemViewModel = ItemViewModel(note)
        }
    }

}
