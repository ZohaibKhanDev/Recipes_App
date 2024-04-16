package com.example.recipesapp.domain.model.worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TotalTimeTier(
    @SerialName("display_tier")
    val displayTier: String,
    @SerialName("tier")
    val tier: String
)