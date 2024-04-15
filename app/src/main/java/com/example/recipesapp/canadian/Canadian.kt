package com.example.recipesapp.canadian


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Canadian(
    @SerialName("meals")
    val meals: List<Meal>
)