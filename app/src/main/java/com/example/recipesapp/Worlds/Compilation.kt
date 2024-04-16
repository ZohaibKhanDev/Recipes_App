package com.example.recipesapp.Worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Compilation(
    @SerialName("approved_at")
    val approvedAt: Int,
    @SerialName("aspect_ratio")
    val aspectRatio: String?,
    @SerialName("beauty_url")
    val beautyUrl: String?,
    @SerialName("buzz_id")
    val buzzId: Int?,
    @SerialName("canonical_id")
    val canonicalId: String,
    @SerialName("country")
    val country: String,
    @SerialName("created_at")
    val createdAt: Int,
    @SerialName("description")
    val description: String?,
    @SerialName("draft_status")
    val draftStatus: String,
    @SerialName("facebook_posts")
    val facebookPosts: List<String>,
    @SerialName("id")
    val id: Int,
    @SerialName("is_shoppable")
    val isShoppable: Boolean,
    @SerialName("keywords")
    val keywords: String?,
    @SerialName("language")
    val language: String,
    @SerialName("name")
    val name: String,
    @SerialName("promotion")
    val promotion: String,
    @SerialName("show")
    val show: List<ShowX>,
    @SerialName("slug")
    val slug: String,
    @SerialName("thumbnail_alt_text")
    val thumbnailAltText: String,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String,
    @SerialName("video_id")
    val videoId: Int,
    @SerialName("video_url")
    val videoUrl: String
)