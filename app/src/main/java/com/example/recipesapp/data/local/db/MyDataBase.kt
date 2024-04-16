package com.example.recipesapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipesapp.data.local.dao.FavDao
import com.example.recipesapp.domain.model.favourite.Fav
import org.koin.core.annotation.Single

@Single
@Database(entities = [Fav::class], version = 1)
abstract class MyDataBase :RoomDatabase(){
    abstract fun getfav(): FavDao
}