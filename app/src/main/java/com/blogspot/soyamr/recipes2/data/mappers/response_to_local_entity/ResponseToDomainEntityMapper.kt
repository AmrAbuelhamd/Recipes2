package com.blogspot.soyamr.recipes2.data.mappers.response_to_local_entity

import com.blogspot.soyamr.recipes2.data.database.dao.ShortRecipeInfoDao
import com.blogspot.soyamr.recipes2.data.database.model.RecipeDetailedInfoEntity
import com.blogspot.soyamr.recipes2.data.mappers.local_entity_to_domain_entity.toDomain
import com.blogspot.soyamr.recipes2.domain.entities.model.RecipeDetailedInfo
import java.util.*
import javax.inject.Inject

class ResponseToDomainEntityMapper @Inject constructor(
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