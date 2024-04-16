package com.example.recipesapp.api
import Canadian
import androidx.room.Query
import com.example.recipesapp.Worlds.World
import com.example.recipesapp.detail.Detail
import com.example.recipesapp.navigation.Screen
import com.example.recipesapp.search.Search


interface ApiClient {

    suspend fun getCountry(): Indian
    suspend fun getCanadian(): Canadian
    suspend fun getWorld(): World
    suspend fun Details(id: String): Detail

    suspend fun Search(query: String):Search
}