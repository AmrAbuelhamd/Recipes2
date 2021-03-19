package com.blogspot.soyamr.recipes2.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.blogspot.soyamr.recipes2.domain.model.RecipeDetailedInfo
import com.blogspot.soyamr.recipes2.domain.model.RecipeInfo


@Entity
data class Recipe(
    val description: String?,
    val difficulty: Int,
    @TypeConverters(Converters::class)
    val images: List<String>,
    val instructions: String,
    val lastUpdated: Int,
    val name: String,
    @PrimaryKey val uuid: String
) {
    public fun toDomainDetailedRecipeInfo() =
        RecipeDetailedInfo(description, difficulty, images, instructions, name)

    public fun toDomainRecipeInfo() =
        RecipeInfo(images[0], name, description, uuid)
}