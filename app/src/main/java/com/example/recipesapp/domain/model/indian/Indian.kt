package com.example.recipesapp.domain.model.indian


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Indian(
    @SerialName("meals")
    val meals: List<Meal>
)