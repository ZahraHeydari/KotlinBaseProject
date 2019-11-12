package com.android.kotlinbaseproject.presentation.home

import android.os.Bundle
import com.android.kotlinbaseproject.R
import com.android.kotlinbaseproject.databinding.ActivityHomeBinding
import com.android.kotlinbaseproject.presentation.note.NotesFragment
import com.android.kotlinbaseproject.presentation.base.BaseActivity
import com.android.kotlinbaseproject.util.extensions.TransitionAnimation
import com.android.kotlinbaseproject.util.extensions.newFragmentInstance
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {

    override var frameContainerId: Int = R.id.homeContainer
    override val layoutId: Int = R.layout.activity_home
    override val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateTo(
            newFragmentInstance<NotesFragment>(),
            NotesFragment.FRAGMENT_NAME,
            TransitionAnimation.FADE,
            false
        )
    }
}
