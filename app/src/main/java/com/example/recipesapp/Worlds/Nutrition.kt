package com.example.recipesapp.Worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Nutrition(
    @SerialName("calories")
    val calories: Int,
    @SerialName("carbohydrates")
    val carbohydrates: Int,
    @SerialName("fat")
    val fat: Int,
    @SerialName("fiber")
    val fiber: Int,
    @SerialName("protein")
    val protein: Int,
    @SerialName("sugar")
    val sugar: Int,
    @SerialName("updated_at")
    val updatedAt: String
)