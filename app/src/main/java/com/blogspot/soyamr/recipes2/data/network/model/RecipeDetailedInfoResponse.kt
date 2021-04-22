package com.blogspot.soyamr.recipes2.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDetailedInfoResponse(
    val uuid: String,
    val name: String,
    val images: List<String>,
    val lastUpdated: Int,
    val description: String? = null,
    val instructions: String,
    val difficulty: Int,
    val similar: List<ShortRecipeResponse>
)