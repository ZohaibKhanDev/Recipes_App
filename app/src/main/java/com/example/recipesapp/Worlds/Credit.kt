package com.example.recipesapp.Worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credit(
    @SerialName("name")
    val name: String?,
    @SerialName("type")
    val type: String
)