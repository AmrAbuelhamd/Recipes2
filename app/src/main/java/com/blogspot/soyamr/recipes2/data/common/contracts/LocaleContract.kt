package com.blogspot.soyamr.recipes2.data.common.contracts

import com.blogspot.soyamr.recipes2.data.local.db.entities.RecipeDetailedInfoEntity
import com.blogspot.soyamr.recipes2.data.local.db.entities.RecipeEntity

interface LocaleContract {

    interface UtilsRepository {
        suspend fun clearDatabase()
    }

    interface RecipeRepository {
        suspend fun getRecipes(keyWord: String): List<RecipeEntity>
        suspend fun getRecipesCount(): Int
        suspend fun getRecipeDetails(id: String): RecipeDetailedInfoEntity?
    }
}