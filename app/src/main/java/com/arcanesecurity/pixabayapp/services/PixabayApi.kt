package com.arcanesecurity.pixabayapp.services

import com.arcanesecurity.pixabayapp.BuildConfig
import com.arcanesecurity.pixabayapp.model.PixabayImage
import com.arcanesecurity.pixabayapp.model.PixabayVideo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api/")
    suspend fun fetchImage(@Query("key") key : String = BuildConfig.API_KEY, @Query("q") q: String, @Query("lang") lang: String = "pt") : Response<PixabayImage>

    @GET("/api/videos")
    suspend fun fetchVideos(@Query("key") key : String = BuildConfig.API_KEY, @Query("q") q: String, @Query("lang") lang: String = "pt") : Response<PixabayVideo>

}