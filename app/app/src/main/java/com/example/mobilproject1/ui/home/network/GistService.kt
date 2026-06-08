package com.example.mobilproject1.ui.home.network

import com.example.mobilproject1.ui.home.model.MemoriesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GistService {
    @GET("Cristhian-LR/bce2d0b164d66e06a92b0f12973f8578/raw/30abc09bf0f242ad4355b57759e7714d4bbcef01/memories.json")
    suspend fun getMemories(): MemoriesResponse
}

object GistClient {
    val service: GistService by lazy {
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GistService::class.java)
    }
}