package com.example.recipesapp.domain.model.worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Price(
    @SerialName("consumption_portion")
    val consumptionPortion: Int,
    @SerialName("consumption_total")
    val consumptionTotal: Int,
    @SerialName("portion")
    val portion: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("updated_at")
    val updatedAt: String
)