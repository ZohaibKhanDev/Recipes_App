package com.example.recipesapp.api

import Canadian
import com.example.recipesapp.Worlds.World
import com.example.recipesapp.database.Fav
import com.example.recipesapp.database.MyDataBase
import com.example.recipesapp.detail.Detail
import com.example.recipesapp.search.Search


class Repository(private val dataBase: MyDataBase):ApiClient {
    override suspend fun getCountry(): Indian {
        return IndianApiClient.Indian()
    }

  override suspend fun getCanadian(): Canadian {
        return IndianApiClient.Canadian()
    }

    override suspend fun getWorld(): World {
        return IndianApiClient.WorldRecipe()
    }

    override suspend fun Details(id: String): Detail {
        return IndianApiClient.Details(id)
    }

    override suspend fun Search(query: String): Search {
        return IndianApiClient.Search(query)
    }

    fun getAllFav():List<Fav>{
        return dataBase.getfav().getDao()
    }

    fun Insert(fav: Fav){
        return dataBase.getfav().Insert(fav)
    }


}