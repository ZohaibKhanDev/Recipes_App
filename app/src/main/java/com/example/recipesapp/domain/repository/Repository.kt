package com.example.recipesapp.domain.repository

import Canadian
import com.example.recipesapp.data.remote.IndianApiClient
import com.example.recipesapp.domain.model.favourite.Fav
import com.example.recipesapp.data.local.db.MyDataBase
import com.example.recipesapp.data.repository.ApiClient
import com.example.recipesapp.domain.model.detail.Detail
import com.example.recipesapp.domain.model.indian.Indian
import com.example.recipesapp.domain.model.search.Search


class Repository(private val dataBase: MyDataBase): ApiClient {
    override suspend fun getCountry(): Indian {
        return IndianApiClient.Indian()
    }

  override suspend fun getCanadian(): Canadian {
        return IndianApiClient.Canadian()
    }

    override suspend fun getWorld(): com.example.recipesapp.domain.model.worlds.World {
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