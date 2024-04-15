package com.example.recipesapp.api

import com.example.recipesapp.canadian.Canadian

interface ApiClient {
    suspend fun getCountry():Indian
    suspend fun getCanadian():Canadian
}