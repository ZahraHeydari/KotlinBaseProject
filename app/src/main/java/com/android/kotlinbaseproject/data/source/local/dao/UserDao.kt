package com.android.kotlinbaseproject.data.source.local.dao

import androidx.room.*
import com.android.kotlinbaseproject.data.model.UserEntity

/**
 * it provides access to [UserEntity] underlying database
 * */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity): Long

    @Query("SELECT * FROM UserEntity")
    fun loadAll(): MutableList<UserEntity>

    @Delete
    fun delete(user: UserEntity)

    @Query("DELETE FROM UserEntity")
    fun deleteAll()

    @Query("SELECT * FROM UserEntity where id = :userId")
    fun loadOneByUserId(userId: Long): UserEntity?

    @Query("SELECT * FROM UserEntity where name = :userName")
    fun loadOneByUserTitle(userName: String): UserEntity?

    @Update
    fun update(user: UserEntity)

}