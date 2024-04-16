package com.example.recipesapp.domain.model.worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRatings(
    @SerialName("count_negative")
    val countNegative: Int,
    @SerialName("count_positive")
    val countPositive: Int,
    @SerialName("score")
    val score: Double
)