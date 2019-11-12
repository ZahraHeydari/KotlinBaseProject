package com.android.kotlinbaseproject.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.kotlinbaseproject.data.model.UserEntity
import com.android.kotlinbaseproject.data.source.local.dao.UserDao

/**
 * To manage data items that can be accessed, updated
 * & maintain relationships between them
 *
 * @Created by ZARA
 */
@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val photoDao: UserDao

    companion object {
        const val DB_NAME = "AppDatabase.db"
    }
}
