package com.example.recipesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipesapp.domain.model.favourite.Fav

@Dao
interface FavDao {
    @Query("SELECT * FROM Fav")
    fun getDao():List<Fav>

    @Insert
    fun Insert(fav: Fav)
}