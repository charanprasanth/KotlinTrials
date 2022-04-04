package com.example.kotlintrials.services

import com.example.kotlintrials.model.PostModel
import com.example.kotlintrials.model.DataModelItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {

    @GET("todos/")
    fun getDataFromApi(): Call<List<DataModelItem>>

    @POST("posts/")
    suspend fun pushData(@Body post: PostModel): Response<PostModel>
}