package com.example.recipesapp.domain.model.worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    @SerialName("display_name")
    val displayName: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("root_tag_type")
    val rootTagType: String,
    @SerialName("type")
    val type: String
)