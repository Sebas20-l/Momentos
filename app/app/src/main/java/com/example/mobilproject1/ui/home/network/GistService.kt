package com.example.mobilproject1.ui.home.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class GistFile(
    val filename: String,
    val content: String
)

data class GistResponse(
    val files: Map<String, GistFile>
)

interface GistService {
    @GET("gists/79da75bb443de39d6368d02cf13e92fb")
    suspend fun getGist(): GistResponse
}

object GistClient {
    val service: GistService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GistService::class.java)
    }
}