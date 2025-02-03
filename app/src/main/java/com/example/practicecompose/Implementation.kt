package com.example.practicecompose

import androidx.compose.runtime.MutableIntState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val client: HttpClient = HttpClient(CIO){
    install(ContentNegotiation){
        json(
            Json {
                ignoreUnknownKeys = true
            }
        )
    }
}

suspend fun sendEmail(email: String, code: Int): PostResponse {
    val request = PostRequest(email = email, code = code)
    return client.post(HttpRoutes.API) {
        contentType(ContentType.Application.Json)
        setBody(request)
    }.body()
}