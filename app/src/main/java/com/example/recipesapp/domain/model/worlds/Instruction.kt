package com.example.recipesapp.domain.model.worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Instruction(
    @SerialName("appliance")
    val appliance: String?,
    @SerialName("display_text")
    val displayText: String,
    @SerialName("end_time")
    val endTime: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("position")
    val position: Int,
    @SerialName("start_time")
    val startTime: Int,
    @SerialName("temperature")
    val temperature: Int?
)