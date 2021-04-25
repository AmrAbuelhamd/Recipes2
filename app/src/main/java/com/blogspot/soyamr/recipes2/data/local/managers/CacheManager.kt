package com.blogspot.soyamr.recipes2.data.local.managers

import com.blogspot.soyamr.recipes2.data.local.db.dao.RecipeDao
import com.blogspot.soyamr.recipes2.data.local.db.dao.RecipeDetailedInfoDao
import com.blogspot.soyamr.recipes2.data.local.db.dao.ShortRecipeInfoDao
import com.blogspot.soyamr.recipes2.data.common.mappers.response_to_local_entity.toLocalEntity
import com.blogspot.soyamr.recipes2.data.network.model.RecipeDetailedInfoResponse
import com.blogspot.soyamr.recipes2.data.network.model.RecipeResponse

class CacheManager(
    private val recipeDao: RecipeDao,
    private val recipeDetailedInfoDao: RecipeDetailedInfoDao,
    private val shortRecipeInfoDao: ShortRecipeInfoDao,

) {
    suspend fun RecipeResponse.cache() {
        recipeDao.insert(toLocalEntity())
    }
    suspend fun RecipeDetailedInfoResponse.cache(){
        similar.map { shortRecipeInfoDao.insert(it.toLocalEntity()) }
        recipeDetailedInfoDao.insert(toLocalEntity())
    }
}