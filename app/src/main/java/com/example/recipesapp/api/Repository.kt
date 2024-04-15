package com.example.recipesapp.api

import com.example.recipesapp.canadian.Canadian
import com.example.recipesapp.detail.Detail

class Repository:ApiClient {
    override suspend fun getCountry(): Indian {
        return IndianApiClient.Indian()
    }

    override suspend fun getCanadian(): Canadian {
        return IndianApiClient.Canadian()
    }

    override suspend fun Details(id: String): Detail {
        return IndianApiClient.Details(id)
    }


}