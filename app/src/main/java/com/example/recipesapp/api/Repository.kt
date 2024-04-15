package com.example.recipesapp.api

import com.example.recipesapp.canadian.Canadian
import com.example.recipesapp.database.Fav
import com.example.recipesapp.database.MyDataBase
import com.example.recipesapp.detail.Detail

class Repository(private val dataBase: MyDataBase):ApiClient {
    override suspend fun getCountry(): Indian {
        return IndianApiClient.Indian()
    }

    override suspend fun getCanadian(): Canadian {
        return IndianApiClient.Canadian()
    }

    override suspend fun Details(id: String): Detail {
        return IndianApiClient.Details(id)
    }

    fun getAllFav():List<Fav>{
        return dataBase.getfav().getDao()
    }

    fun Insert(fav: Fav){
        return dataBase.getfav().Insert(fav)
    }


}