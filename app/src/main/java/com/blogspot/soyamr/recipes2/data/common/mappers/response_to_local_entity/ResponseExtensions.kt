package com.blogspot.soyamr.recipes2.data.common.mappers.response_to_local_entity

import com.blogspot.soyamr.recipes2.data.database.converters.Ids
import com.blogspot.soyamr.recipes2.data.database.converters.Photos
import com.blogspot.soyamr.recipes2.data.database.model.RecipeDetailedInfoEntity
import com.blogspot.soyamr.recipes2.data.database.model.RecipeEntity
import com.blogspot.soyamr.recipes2.data.database.model.ShortRecipeEntity
import com.blogspot.soyamr.recipes2.data.network.model.RecipeDetailedInfoResponse
import com.blogspot.soyamr.recipes2.data.network.model.RecipeResponse
import com.blogspot.soyamr.recipes2.data.network.model.ShortRecipeResponse

fun RecipeResponse.toLocalEntity() =
    RecipeEntity(uuid, name, Photos(images), lastUpdated, description, instructions, difficulty)

fun RecipeDetailedInfoResponse.toLocalEntity() =
    RecipeDetailedInfoEntity(
        uuid,
        name,
        Photos(images),
        lastUpdated,
        description,
        instructions,
        difficulty,
        Ids(similar.map { it.uuid })
    )

fun ShortRecipeResponse.toLocalEntity() =
    ShortRecipeEntity(uuid, name, image)
