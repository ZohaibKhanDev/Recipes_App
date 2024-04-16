package com.example.recipesapp.domain.model.worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class World(
    @SerialName("count")
    val count: Int?=null,
    @SerialName("results")
    val results: List<com.example.recipesapp.domain.model.worlds.Result>?=null
)