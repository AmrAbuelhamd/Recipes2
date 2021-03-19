package com.blogspot.soyamr.recipes2.domain.model

class RecipeDetailedInfo(
    val description: String?,
    val difficulty: Int,
    val images: List<String>?,
    val instructions: String,
    val name: String,
)