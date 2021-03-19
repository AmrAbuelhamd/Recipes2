package com.blogspot.soyamr.recipes2.data.network.model

import com.blogspot.soyamr.recipes2.data.database.model.Recipe as dbRecipe


data class Recipe(
    val description: String?,
    val difficulty: Int,
    val images: List<String>,
    val instructions: String,
    val lastUpdated: Int,
    val name: String,
    val uuid: String
) {
    fun toDataBaseRecipe() =
        dbRecipe(description, difficulty, images, instructions, lastUpdated, name, uuid)
}