package com.example.recipesapp.Worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class World(
    @SerialName("count")
    val count: Int?=null,
    @SerialName("results")
    val results: List<Result>?=null
)