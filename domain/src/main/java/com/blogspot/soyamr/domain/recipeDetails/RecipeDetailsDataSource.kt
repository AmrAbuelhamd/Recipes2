package com.blogspot.soyamr.domain.recipeDetails

import com.blogspot.soyamr.domain.recipeDetails.model.RecipeDetailedInfo

interface RecipeDetailsDataSource {
    suspend fun getRecipeDetails(id: String): Result<RecipeDetailedInfo>
}