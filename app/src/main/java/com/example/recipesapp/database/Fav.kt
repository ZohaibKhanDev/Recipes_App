package com.example.recipesapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fav(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    @ColumnInfo("image")
    val image:String,
    @ColumnInfo("tittle")
    val tittle:String,
    @ColumnInfo("des")
    val des:String
)
