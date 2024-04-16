package com.example.recipesapp.data.remote

import Canadian
import com.example.recipesapp.domain.model.detail.Detail
import com.example.recipesapp.domain.model.indian.Indian
import com.example.recipesapp.domain.model.search.Search
import com.example.recipesapp.utils.Constant.TIMEOUT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
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

   suspend fun Canadian(): Canadian {
        return client.get("https://themealdb.com/api/json/v1/1/filter.php?a=Canadian").body()
    }

    suspend fun Indian(): Indian {
        return client.get("https://themealdb.com/api/json/v1/1/filter.php?a=Indian").body()
    }

    suspend fun Details(id:String): Detail {
        return client.get("https://www.themealdb.com/api/json/v1/1/lookup.php?i=$id").body()
    }

    suspend fun WorldRecipe(): com.example.recipesapp.domain.model.worlds.World {
        return client.get("https://tasty.p.rapidapi.com/recipes/list?from=0&size=20&tags=under_30_minutes"){
            headers {
                append("X-RapidAPI-Key","65951b8989msha06ada35f2357eap14a406jsn7d21338fec93")
                append("X-RapidAPI-Host","tasty.p.rapidapi.com")
            }
        }.body()
    }

    suspend fun Search(query: String): Search {
        return client.get("https://themealdb.com/api/json/v1/1/search.php?s=${query}").body()
    }

}