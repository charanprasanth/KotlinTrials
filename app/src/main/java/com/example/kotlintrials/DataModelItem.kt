package com.example.kotlintrials

import com.google.gson.annotations.SerializedName

data class DataModelItem(
    @SerializedName("completed")
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)

data class Post (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)