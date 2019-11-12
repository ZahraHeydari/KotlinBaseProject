package com.android.kotlinbaseproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserEntity")
data class UserEntity(
    @PrimaryKey var id: Long,
    var name: String,
    val imageUrl: String,
    val thumbnailUrl: String?
)