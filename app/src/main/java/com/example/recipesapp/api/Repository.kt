package com.example.recipesapp.api

import com.example.recipesapp.canadian.Canadian

class Repository:ApiClient {
    override suspend fun getCountry(): Indian {
        return IndianApiClient.Indian()
    }

    override suspend fun getCanadian(): Canadian {
        return IndianApiClient.Canadian()
    }

}