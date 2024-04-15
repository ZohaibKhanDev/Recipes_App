package com.example.recipesapp.api

import com.example.recipesapp.api.Constant.TIMEOUT
import com.example.recipesapp.canadian.Canadian
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object IndianApiClient {
    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                    prettyPrint = true
                }
            )
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }

            }
        }

        install(HttpTimeout){
            connectTimeoutMillis= TIMEOUT
            socketTimeoutMillis= TIMEOUT
            requestTimeoutMillis= TIMEOUT
        }
    }

    suspend fun Canadian():Canadian{
        return client.get("https://themealdb.com/api/json/v1/1/filter.php?a=Canadian").body()
    }

    suspend fun Indian(): Indian {
        return client.get("https://themealdb.com/api/json/v1/1/filter.php?a=Indian").body()
    }

}