package com.android.kotlinbaseproject.presentation.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import com.android.kotlinbaseproject.util.extensions.TransitionAnimation
import com.android.kotlinbaseproject.util.extensions.setCustomAnimation

abstract class BaseActivity<V : BaseViewModel, B : ViewDataBinding>: AppCompatActivity(), BaseViewGroup<V, B>, NavigationListener {

    final override lateinit var binding: B
    abstract var frameContainerId: Int
    val backCallback: MutableLiveData<OnBackPressedListener?> = MutableLiveData()
    private var lastFragmentTag = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
    }

    override fun onBackPressed() {
        if (backCallback.value == null) {
            if (!canBack())
                super.onBackPressed()
        } else {
            if (backCallback.value?.onBackPressed(this) == false){
                if (!canBack()) {
                    super.onBackPressed()
                }
            }
        }
    }

    private fun canBack(): Boolean {
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
            return true
        }

        supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
            .name?.let {
            supportFragmentManager.findFragmentByTag(it)?.apply {
                if (childFragmentManager.backStackEntryCount != 0) {
                    childFragmentManager.popBackStack()
                    return true
                }

            }
        }
        return false
    }

    override fun onDestroy() {
        if (backCallback.value != null) {
            backCallback.value = null
        }
        super.onDestroy()
    }

    override fun navigateTo(fragment: Fragment, backStackTag: String, animationType: TransitionAnimation, isAdd: Boolean) {
        if (lastFragmentTag.equals(backStackTag, ignoreCase = true)) {
            Log.e("BaseActivity", "Cannot navigate to the current fragment. It's already visible on the screen")
            return
        }

        if (frameContainerId == 0) {
            Log.e("BaseActivity", "No container is defined to navigate on!")
            return
        }

        if (supportFragmentManager == null) {
            Log.e("BaseActivity", "supportFragmentManager is null")
            return
        }

        if (supportFragmentManager.backStackEntryCount > 0 && isAdd) {
            val tag = supportFragmentManager
                .getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
                .name

            supportFragmentManager.findFragmentByTag(tag)?.apply {
                this.onPause()
            }
        }

        supportFragmentManager.findFragmentByTag(backStackTag)?.let {
            supportFragmentManager.popBackStack(backStackTag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            return
        }


        supportFragmentManager.beginTransaction().apply {
            setCustomAnimation(animationType)
            if (isAdd) {
                add(frameContainerId, fragment, backStackTag)
            } else {
                replace(frameContainerId, fragment, backStackTag)
            }
            //addToBackStack(backStackTag)
            commitAllowingStateLoss()
        }

        lastFragmentTag = backStackTag
    }
}