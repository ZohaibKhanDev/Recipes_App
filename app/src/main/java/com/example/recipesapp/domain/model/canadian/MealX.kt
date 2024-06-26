package com.example.recipesapp.domain.model.canadian


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealX(
    @SerialName("idMeal")
    val idMeal: String,
    @SerialName("strMeal")
    val strMeal: String,
    @SerialName("strMealThumb")
    val strMealThumb: String
)