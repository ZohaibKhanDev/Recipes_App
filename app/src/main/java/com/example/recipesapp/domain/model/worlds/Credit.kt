package com.example.recipesapp.domain.model.worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credit(
    @SerialName("name")
    val name: String?,
    @SerialName("type")
    val type: String
)