package com.blogspot.soyamr.recipes2.data.common.mappers.local_entity_to_domain_entity

import com.blogspot.soyamr.recipes2.data.local.db.dao.ShortRecipeInfoDao
import com.blogspot.soyamr.recipes2.data.local.db.entities.RecipeDetailedInfoEntity
import com.blogspot.soyamr.recipes2.domain.entities.model.RecipeDetailedInfo
import java.util.*
import javax.inject.Inject

class LocalEntityToDomainEntityMapper @Inject constructor(
private val shortRecipeInfoDao: ShortRecipeInfoDao
) {
    fun RecipeDetailedInfoEntity.toDomain() = RecipeDetailedInfo(
        name,
        images.urls,
        Date((lastUpdated.toString() + "000").toLong()),
        description,
        instructions,
        difficulty,
        similar.ids.map { shortRecipeInfoDao.findRecipeById(it).toDomain() }.toList()
    )
}