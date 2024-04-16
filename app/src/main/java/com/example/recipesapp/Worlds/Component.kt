package com.example.recipesapp.Worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Component(
    @SerialName("extra_comment")
    val extraComment: String,
    @SerialName("id")
    val id: Int,
    @SerialName("ingredient")
    val ingredient: Ingredient,
    @SerialName("measurements")
    val measurements: List<Measurement>,
    @SerialName("position")
    val position: Int,
    @SerialName("raw_text")
    val rawText: String
)