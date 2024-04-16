package com.example.recipesapp.Worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Section(
    @SerialName("components")
    val components: List<Component>,
    @SerialName("name")
    val name: String?,
    @SerialName("position")
    val position: Int
)