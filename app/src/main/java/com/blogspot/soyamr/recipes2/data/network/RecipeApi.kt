package com.blogspot.soyamr.recipes2.data.network

import com.blogspot.soyamr.recipes2.data.network.model.JsonResponse
import retrofit2.http.GET

interface RecipeApi {

    @GET("recipes.json")
    suspend fun getRecipes(): JsonResponse
}