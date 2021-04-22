package com.blogspot.soyamr.recipes2.data.network.model.response_wrapper

import com.blogspot.soyamr.recipes2.data.network.model.RecipeDetailedInfoResponse
import kotlinx.serialization.Serializable

@Serializable
data class  RecipeDetailedInfoResponseWrapper (
    val recipe: RecipeDetailedInfoResponse
)