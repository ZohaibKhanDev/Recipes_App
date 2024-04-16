package com.example.recipesapp.Worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rendition(
    @SerialName("aspect")
    val aspect: String,
    @SerialName("bit_rate")
    val bitRate: Int?,
    @SerialName("container")
    val container: String,
    @SerialName("content_type")
    val contentType: String,
    @SerialName("duration")
    val duration: Int,
    @SerialName("file_size")
    val fileSize: Int?,
    @SerialName("height")
    val height: Int,
    @SerialName("maximum_bit_rate")
    val maximumBitRate: Int?,
    @SerialName("minimum_bit_rate")
    val minimumBitRate: Int?,
    @SerialName("name")
    val name: String,
    @SerialName("poster_url")
    val posterUrl: String,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int
)