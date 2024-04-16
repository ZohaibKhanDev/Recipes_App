package com.example.recipesapp.Worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    @SerialName("created_at")
    val createdAt: Int,
    @SerialName("display_plural")
    val displayPlural: String,
    @SerialName("display_singular")
    val displaySingular: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("updated_at")
    val updatedAt: Int
)