package com.example.recipesapp.domain.model.worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TipsSummary(
    @SerialName("by_line")
    val byLine: String,
    @SerialName("content")
    val content: String,
    @SerialName("header")
    val header: String
)