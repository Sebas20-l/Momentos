package com.example.mobilproject1.ui.home.model

data class Memory(
    val title: String,
    val date: String,
    val description: String,
    val photoUrl: String
)

data class MemoriesResponse(
    val memories: List<Memory>
)