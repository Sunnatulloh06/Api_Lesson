package com.example.practicecompose

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val email: String,
    val code: Int
)
