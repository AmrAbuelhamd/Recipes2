package com.blogspot.soyamr.recipes2.data.common.mappers.response_to_local_entity

import com.blogspot.soyamr.recipes2.data.local.db.converters.Ids
import com.blogspot.soyamr.recipes2.data.local.db.converters.Photos
import com.blogspot.soyamr.recipes2.data.local.db.entities.RecipeDetailedInfoEntity
import com.blogspot.soyamr.recipes2.data.local.db.entities.RecipeEntity
import com.blogspot.soyamr.recipes2.data.local.db.entities.ShortRecipeEntity
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
