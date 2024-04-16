package com.example.recipesapp.domain.model.worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Measurement(
    @SerialName("id")
    val id: Int,
    @SerialName("quantity")
    val quantity: String,
    @SerialName("unit")
    val unit: com.example.recipesapp.domain.model.worlds.Unit
)