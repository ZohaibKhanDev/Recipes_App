package com.example.recipesapp.Worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Measurement(
    @SerialName("id")
    val id: Int,
    @SerialName("quantity")
    val quantity: String,
    @SerialName("unit")
    val unit: Unit
)