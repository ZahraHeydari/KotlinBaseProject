package com.android.kotlinbaseproject

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.android.kotlinbaseproject.di.module.DatabaseModule
import com.android.kotlinbaseproject.di.module.HomeModule
import com.android.kotlinbaseproject.di.module.NetworkModule
import com.android.kotlinbaseproject.util.AppConstants
import com.android.kotlinbaseproject.util.helper.LocaleHelper
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    private val TAG = MainApplication::class.java.name


    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Stetho.initializeWithDefaults(this)

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(HomeModule, NetworkModule, DatabaseModule))
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(base, AppConstants.DEFAULT_LANGUAGE))
        MultiDex.install(this)
    }

}