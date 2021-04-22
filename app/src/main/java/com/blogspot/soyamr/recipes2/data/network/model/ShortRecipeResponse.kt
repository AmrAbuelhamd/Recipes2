package com.blogspot.soyamr.recipes2.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ShortRecipeResponse(
    val uuid: String,
    val name: String,
    val image: String,
)
