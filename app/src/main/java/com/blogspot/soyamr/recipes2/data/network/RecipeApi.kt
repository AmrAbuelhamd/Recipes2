package com.blogspot.soyamr.recipes2.data.network

import com.blogspot.soyamr.recipes2.data.network.model.response_wrapper.RecipeDetailedInfoResponseWrapper
import com.blogspot.soyamr.recipes2.data.network.model.response_wrapper.RecipesListResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApi {

    @GET("recipes")
    suspend fun getRecipes(): RecipesListResponseWrapper

    @GET("recipes/{uuid}")
    suspend fun getRecipeDetailedInfo(@Path("uuid") uuid: String): RecipeDetailedInfoResponseWrapper
}