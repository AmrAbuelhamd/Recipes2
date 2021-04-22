package com.blogspot.soyamr.recipes2.domain

import com.blogspot.soyamr.recipes2.domain.entities.Result
import com.blogspot.soyamr.recipes2.domain.entities.SortType
import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import com.blogspot.soyamr.recipes2.domain.entities.model.RecipeDetailedInfo
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipes(sortType: SortType, keyWord: String): Result<List<Recipe>>
    suspend fun getRecipeDetails(id: String): Result<RecipeDetailedInfo>
    suspend fun updateRecipes(): Result<Unit>
}