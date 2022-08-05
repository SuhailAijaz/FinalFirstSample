package com.example.sampleproject1.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostsInstance {
    private val BaseUrl="https://jsonplaceholder.typicode.com/"
    private val photosInterface:PostsInterface?=null
    fun getPhotosService():PostsInterface{
        return Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build().
        create(PostsInterface::class.java)
        return photosInterface!!
    }
}