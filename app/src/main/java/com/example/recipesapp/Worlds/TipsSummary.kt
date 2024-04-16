package com.example.recipesapp.Worlds


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