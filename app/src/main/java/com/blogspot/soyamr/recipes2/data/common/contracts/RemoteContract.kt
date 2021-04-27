package com.blogspot.soyamr.recipes2.data.common.contracts

import com.blogspot.soyamr.recipes2.data.network.model.RecipeDetailedInfoResponse
import com.blogspot.soyamr.recipes2.data.network.model.RecipeResponse
import com.blogspot.soyamr.recipes2.data.network.model.response_wrapper.RecipeDetailedInfoResponseWrapper
import com.blogspot.soyamr.recipes2.data.network.model.response_wrapper.RecipesListResponseWrapper

interface RemoteContract {
    interface RecipeRepository {
        suspend fun getRecipes(): List<RecipeResponse>
        suspend fun getRecipeDetailedInfo(uuid: String): RecipeDetailedInfoResponse
    }
}