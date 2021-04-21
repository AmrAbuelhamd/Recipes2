package com.blogspot.soyamr.recipes2.domain

import com.blogspot.soyamr.recipes2.domain.model.RecipeDetailedInfo
import com.blogspot.soyamr.recipes2.domain.model.RecipeInfo
import com.blogspot.soyamr.recipes2.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getRecipes(sort: Sort): Flow<List<RecipeInfo>>
    suspend fun updateRecipes(): Result<Unit>
    suspend fun getRecipeDetails(id: String): Result<RecipeDetailedInfo>
    fun queryRecipes(keyWord: String): Flow<List<RecipeInfo>>
}