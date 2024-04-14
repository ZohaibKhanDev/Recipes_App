package com.example.recipesapp.api


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Indian(
    @SerialName("meals")
    val meals: List<Meal>
)