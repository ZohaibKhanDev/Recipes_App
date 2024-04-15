package com.example.recipesapp.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Fav::class], version = 1)
abstract class MyDataBase :RoomDatabase(){
    abstract fun getfav(): FavDao
}