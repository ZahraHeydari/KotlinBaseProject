package com.android.kotlinbaseproject.presentation.base

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel() {

    val showProgress: ObservableField<Boolean> = ObservableField()
    val networkError: ObservableField<Boolean> = ObservableField()
    val commonMessage: MutableLiveData<String> = MutableLiveData()
    val dataLoaded: ObservableField<Boolean> = ObservableField()

    val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )

    // Cancel the job when the view model is destroyed
    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }


    init {
        showProgress.set(false)
        networkError.set(false)
    }

    open fun tryAgainFunction() {

    }

    open fun showProgressBar() {
        showProgress.set(true)
    }

    open fun hideProgressBar() {
        showProgress.set(false)

    }

    open fun showConnectionError() {
        hideProgressBar()
        networkError.set(true)
    }

    open fun hideConnectionError() {
        networkError.set(false)
    }
}