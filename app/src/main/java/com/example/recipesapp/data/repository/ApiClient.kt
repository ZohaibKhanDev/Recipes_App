package com.example.recipesapp.data.repository
import Canadian
import com.example.recipesapp.domain.model.detail.Detail
import com.example.recipesapp.domain.model.indian.Indian
import com.example.recipesapp.domain.model.search.Search
import org.koin.core.annotation.Single

@Single
interface ApiClient {

    suspend fun getCountry(): Indian
    suspend fun getCanadian(): Canadian
    suspend fun getWorld(): com.example.recipesapp.domain.model.worlds.World
    suspend fun Details(id: String): Detail

    suspend fun Search(query: String): Search
}