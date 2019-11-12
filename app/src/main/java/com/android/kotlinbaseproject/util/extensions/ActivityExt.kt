package com.android.kotlinbaseproject.util.extensions

import android.app.Activity
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.kotlinbaseproject.R


fun AppCompatActivity.replaceFragmentWithAnimation(
    container: View,
    fragment: Fragment,
    tag: String
) {
    Handler().post {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.enter_from_left,
            R.anim.exit_to_right,
            R.anim.enter_from_right,
            R.anim.exit_to_left
        )
        transaction.replace(container.id, fragment)
        transaction.addToBackStack(tag)
        transaction.commitAllowingStateLoss()
    }

}

fun AppCompatActivity.replaceFragmentSlidingUp(container: View, fragment: Fragment, tag: String) {
    Handler().post {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.enter_from_bottom,
            R.anim.exit_to_bottom,
            R.anim.enter_from_bottom,
            R.anim.exit_to_bottom
        )
        transaction.replace(container.id, fragment)
        transaction.addToBackStack(tag)
        transaction.commitAllowingStateLoss()
    }

}

fun Activity.hideKeyboard() {
    if (currentFocus == null) View(this) else currentFocus?.let { hideKeyboard(it) }
}

