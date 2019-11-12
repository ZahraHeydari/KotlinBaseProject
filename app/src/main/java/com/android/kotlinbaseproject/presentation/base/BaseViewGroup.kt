package com.android.kotlinbaseproject.presentation.base

import androidx.databinding.ViewDataBinding

interface BaseViewGroup<V : BaseViewModel, B : ViewDataBinding> {

    val viewModel: V

    val layoutId: Int

    var binding: B

}
