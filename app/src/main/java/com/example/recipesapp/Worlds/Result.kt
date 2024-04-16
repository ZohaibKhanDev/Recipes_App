package com.example.recipesapp.Worlds


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("approved_at")
    val approvedAt: Int,
    @SerialName("aspect_ratio")
    val aspectRatio: String?,
    @SerialName("beauty_url")
    val beautyUrl: String?,
    @SerialName("brand")
    val brand: String?,
    @SerialName("brand_id")
    val brandId: String?,
    @SerialName("buzz_id")
    val buzzId: Int?,
    @SerialName("canonical_id")
    val canonicalId: String,
    @SerialName("compilations")
    val compilations: List<Compilation>,
    @SerialName("cook_time_minutes")
    val cookTimeMinutes: Int?,
    @SerialName("country")
    val country: String,
    @SerialName("created_at")
    val createdAt: Int,
    @SerialName("credits")
    val credits: List<Credit>,
    @SerialName("description")
    val description: String,
    @SerialName("draft_status")
    val draftStatus: String,
    @SerialName("facebook_posts")
    val facebookPosts: List<String>,
    @SerialName("id")
    val id: Int,
    @SerialName("inspired_by_url")
    val inspiredByUrl: String?,
    @SerialName("instructions")
    val instructions: List<Instruction>,
    @SerialName("is_app_only")
    val isAppOnly: Boolean,
    @SerialName("is_one_top")
    val isOneTop: Boolean,
    @SerialName("is_shoppable")
    val isShoppable: Boolean,
    @SerialName("is_subscriber_content")
    val isSubscriberContent: Boolean,
    @SerialName("keywords")
    val keywords: String?,
    @SerialName("language")
    val language: String,
    @SerialName("name")
    val name: String,
    @SerialName("num_servings")
    val numServings: Int,
    @SerialName("nutrition")
    val nutrition: Nutrition,
    @SerialName("nutrition_visibility")
    val nutritionVisibility: String,
    @SerialName("original_video_url")
    val originalVideoUrl: String?,
    @SerialName("prep_time_minutes")
    val prepTimeMinutes: Int?,
    @SerialName("price")
    val price: Price,
    @SerialName("promotion")
    val promotion: String,
    @SerialName("renditions")
    val renditions: List<Rendition>,
    @SerialName("sections")
    val sections: List<Section>,
    @SerialName("seo_path")
    val seoPath: String,
    @SerialName("seo_title")
    val seoTitle: String?,
    @SerialName("servings_noun_plural")
    val servingsNounPlural: String,
    @SerialName("servings_noun_singular")
    val servingsNounSingular: String,
    @SerialName("show")
    val show: ShowX,
    @SerialName("show_id")
    val showId: Int,
    @SerialName("slug")
    val slug: String,
    @SerialName("tags")
    val tags: List<Tag>,
    @SerialName("thumbnail_alt_text")
    val thumbnailAltText: String,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String,
    @SerialName("tips_and_ratings_enabled")
    val tipsAndRatingsEnabled: Boolean,
    @SerialName("tips_summary")
    val tipsSummary: TipsSummary?,
    @SerialName("topics")
    val topics: List<Topic>,
    @SerialName("total_time_minutes")
    val totalTimeMinutes: Int?,
    @SerialName("total_time_tier")
    val totalTimeTier: TotalTimeTier,
    @SerialName("updated_at")
    val updatedAt: Int,
    @SerialName("user_ratings")
    val userRatings: UserRatings,
    @SerialName("video_ad_content")
    val videoAdContent: String?,
    @SerialName("video_id")
    val videoId: Int?,
    @SerialName("video_url")
    val videoUrl: String?,
    @SerialName("yields")
    val yields: String
)