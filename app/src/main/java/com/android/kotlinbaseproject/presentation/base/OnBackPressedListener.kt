package com.android.kotlinbaseproject.presentation.base

import androidx.fragment.app.FragmentActivity

interface OnBackPressedListener {

    fun onBackPressed(activity: FragmentActivity) : Boolean

}
