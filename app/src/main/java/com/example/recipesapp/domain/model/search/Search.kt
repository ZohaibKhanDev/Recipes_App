package com.example.recipesapp.domain.model.search


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Search(
    @SerialName("meals")
    val meals: List<Meal>
)