package com.blogspot.soyamr.recipes2.data.network.model.response_wrapper

import com.blogspot.soyamr.recipes2.data.network.model.RecipeResponse
import kotlinx.serialization.Serializable

@Serializable
data class RecipesListResponseWrapper(
    val recipes: List<RecipeResponse>
)