package com.example.recipesapp.domain.model.detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Detail(
    @SerialName("meals")
    val meals: List<Meal>
)