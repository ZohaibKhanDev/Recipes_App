package com.example.recipesapp.Worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowX(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)