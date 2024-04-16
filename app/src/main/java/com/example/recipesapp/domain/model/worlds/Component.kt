package com.example.recipesapp.domain.model.worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Component(
    @SerialName("extra_comment")
    val extraComment: String,
    @SerialName("id")
    val id: Int,
    @SerialName("ingredient")
    val ingredient: com.example.recipesapp.domain.model.worlds.Ingredient,
    @SerialName("measurements")
    val measurements: List<com.example.recipesapp.domain.model.worlds.Measurement>,
    @SerialName("position")
    val position: Int,
    @SerialName("raw_text")
    val rawText: String
)