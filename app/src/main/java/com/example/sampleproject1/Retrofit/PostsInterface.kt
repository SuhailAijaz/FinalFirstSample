package com.example.sampleproject1.Retrofit

import android.telecom.Call
import retrofit2.Response
import retrofit2.http.GET
interface PostsInterface {
    @GET("posts")
    suspend fun getphotosdata(): Response<PostsData>
}