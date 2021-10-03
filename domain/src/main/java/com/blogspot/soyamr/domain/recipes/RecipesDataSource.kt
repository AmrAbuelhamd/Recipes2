package com.blogspot.soyamr.domain.recipes

import com.blogspot.soyamr.domain.recipes.model.Recipe

interface RecipesDataSource {
    suspend fun getRecipes(keyWord: String): Result<List<Recipe>>
    suspend fun updateRecipes()
}