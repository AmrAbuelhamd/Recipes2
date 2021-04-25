package com.blogspot.soyamr.recipes2.data.network.implementation

import com.blogspot.soyamr.recipes2.data.common.contracts.RemoteContract
import com.blogspot.soyamr.recipes2.data.network.RecipeApi
import com.blogspot.soyamr.recipes2.data.network.model.RecipeDetailedInfoResponse
import com.blogspot.soyamr.recipes2.data.network.model.RecipeResponse
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi,
) : RemoteContract.RecipeRepository {
    override suspend fun getRecipes(): List<RecipeResponse> {
        return recipeApi.getRecipes().recipes
    }

    override suspend fun getRecipeDetailedInfo(uuid: String): RecipeDetailedInfoResponse {
        return recipeApi.getRecipeDetailedInfo(uuid).recipe
    }
}