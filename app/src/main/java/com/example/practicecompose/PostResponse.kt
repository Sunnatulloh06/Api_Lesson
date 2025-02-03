package com.example.practicecompose

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    @SerialName("success" ) var success : Boolean = false,
    @SerialName("message" ) var token : String?  = null
)
