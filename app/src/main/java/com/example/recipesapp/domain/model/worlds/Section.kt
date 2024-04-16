package com.example.recipesapp.domain.model.worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Section(
    @SerialName("components")
    val components: List<com.example.recipesapp.domain.model.worlds.Component>,
    @SerialName("name")
    val name: String?,
    @SerialName("position")
    val position: Int
)