package com.blogspot.soyamr.recipes2.domain.entities.model

import java.util.*

class RecipeDetailedInfo(
    val name: String,
    val images: List<String>,
    val lastUpdated: Date,
    val description: String?,
    val instructions: String,
    val difficulty: Int,
    val similar: List<ShortRecipe>
)