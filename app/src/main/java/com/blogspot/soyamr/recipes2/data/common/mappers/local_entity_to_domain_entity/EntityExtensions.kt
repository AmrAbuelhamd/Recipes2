package com.blogspot.soyamr.recipes2.data.common.mappers.local_entity_to_domain_entity

import com.blogspot.soyamr.recipes2.data.local.db.entities.RecipeEntity
import com.blogspot.soyamr.recipes2.data.local.db.entities.ShortRecipeEntity
import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import com.blogspot.soyamr.recipes2.domain.entities.model.ShortRecipe
import java.util.*

fun RecipeEntity.toDomain() =
    Recipe(uuid, name, images.urls[0], Date((lastUpdated.toString() + "000").toLong()), description)


fun ShortRecipeEntity.toDomain() = ShortRecipe(
    uuid, name, images
)
