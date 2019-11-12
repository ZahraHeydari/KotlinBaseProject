package com.android.kotlinbaseproject.di.module

import android.app.Application
import androidx.room.Room
import com.android.kotlinbaseproject.data.source.local.AppDatabase
import org.koin.dsl.module

val DatabaseModule = module {

    single { createAppDatabase(get()) }

}

internal fun createAppDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        AppDatabase.DB_NAME
    )
        // .fallbackToDestructiveMigration()//allows database to be cleared after upgrading version
        .allowMainThreadQueries()
        .build()
}