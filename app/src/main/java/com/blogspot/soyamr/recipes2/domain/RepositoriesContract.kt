package com.blogspot.soyamr.recipes2.domain

import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import com.blogspot.soyamr.recipes2.domain.entities.model.RecipeDetailedInfo

interface RepositoriesContract {
    interface RecipesRepository {
        suspend fun getRecipes(keyWord: String): Result<List<Recipe>>
        suspend fun updateRecipes()
    }

    interface SingleRecipeRepository {
        suspend fun getRecipeDetails(id: String): Result<RecipeDetailedInfo>
    }

    interface ImageRepository {
        suspend fun downloadImage(url: String): Result<String>
    }
}