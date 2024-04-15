package com.example.recipesapp.api

import com.example.recipesapp.canadian.Canadian
import com.example.recipesapp.detail.Detail

interface ApiClient {
    suspend fun getCountry():Indian
    suspend fun getCanadian():Canadian
    suspend fun Details(id:String):Detail
}