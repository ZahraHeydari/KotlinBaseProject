package com.android.kotlinbaseproject.presentation.base

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import com.android.kotlinbaseproject.R

class CommonToast(context: Context) : Toast(context) {

    companion object {

        const val LENGTH_LONG = Toast.LENGTH_LONG
        const val LENGTH_SHORT = Toast.LENGTH_SHORT

        fun makeToast(context: Context, message: String): CommonToast {
            return makeToast(context, message, LENGTH_SHORT)
        }

        fun makeToast(context: Context, message: String, duration: Int): CommonToast {
            val toast = CommonToast(context)
            val view = LayoutInflater.from(context).inflate(R.layout.custom_toast_container, null)
            ViewCompat.setElevation(view, 16f)
            view.findViewById<TextView>(R.id.toast_text).text = message
            toast.duration = duration
            toast.view = view
            return toast
        }

    }

}