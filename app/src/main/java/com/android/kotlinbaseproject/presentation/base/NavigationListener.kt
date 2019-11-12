package com.android.kotlinbaseproject.presentation.base

import androidx.fragment.app.Fragment
import com.android.kotlinbaseproject.util.extensions.TransitionAnimation

interface NavigationListener {

    fun navigateTo(
        fragment: Fragment,
        backStackTag: String,
        animationType: TransitionAnimation,
        isAdd: Boolean
    )

}