package com.android.kotlinbaseproject.presentation.note

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import com.android.kotlinbaseproject.R
import com.android.kotlinbaseproject.databinding.FragmentNotesBinding
import com.android.kotlinbaseproject.domain.model.Note
import com.android.kotlinbaseproject.presentation.base.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

class NotesFragment : BaseFragment<NotesViewModel, FragmentNotesBinding>() {

    private var adapter: NotesAdapter? = null
    override val viewModel: NotesViewModel by viewModel()
    override var title: String = "Notes"
    override var menuId: Int = 0
    override val toolBarId: Int = 0
    override val layoutId: Int = R.layout.fragment_notes
    override val progressBar: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NotesAdapter()

    }

    @ExperimentalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewmodel = viewModel
        binding.homeRecyclerView.adapter = adapter

        viewModel.loadAlbums()
    }


    companion object {

        val FRAGMENT_NAME = NotesFragment::class.java.name
    }
}