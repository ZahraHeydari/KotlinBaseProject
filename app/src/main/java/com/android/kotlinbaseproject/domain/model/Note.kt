package com.android.kotlinbaseproject.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Note(var userId:Long, var id:Long, var title:String,var completed:Boolean)