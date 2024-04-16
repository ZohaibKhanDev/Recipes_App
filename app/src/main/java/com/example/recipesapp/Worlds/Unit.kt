package com.example.recipesapp.Worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Unit(
    @SerialName("abbreviation")
    val abbreviation: String,
    @SerialName("display_plural")
    val displayPlural: String,
    @SerialName("display_singular")
    val displaySingular: String,
    @SerialName("name")
    val name: String,
    @SerialName("system")
    val system: String
)