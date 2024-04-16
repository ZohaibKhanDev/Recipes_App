package com.example.recipesapp.di

import androidx.room.Room
import com.example.recipesapp.data.local.db.MyDataBase
import com.example.recipesapp.domain.repository.Repository
import com.example.recipesapp.presentation.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            MyDataBase::class.java,
            "demo.db"

        ).allowMainThreadQueries()
            .build()
    }
    single { Repository(get()) }
    viewModelOf(::MainViewModel)
}