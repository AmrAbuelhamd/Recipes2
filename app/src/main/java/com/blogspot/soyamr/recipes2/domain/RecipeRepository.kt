package com.blogspot.soyamr.recipes2.domain

import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import com.blogspot.soyamr.recipes2.domain.entities.model.RecipeDetailedInfo

interface RecipeRepository {
    suspend fun getRecipes(keyWord: String): Result<List<Recipe>>
    suspend fun getRecipeDetails(id: String): Result<RecipeDetailedInfo>
    suspend fun updateRecipes(): Result<Unit>
}