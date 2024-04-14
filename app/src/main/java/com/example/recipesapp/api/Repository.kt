package com.example.recipesapp.api

class Repository:ApiClient {
    override suspend fun getCountry(): Indian {
        return IndianApiClient.getCountry()
    }

}