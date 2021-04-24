package com.blogspot.soyamr.recipes2.data

import com.blogspot.soyamr.recipes2.data.common.mappers.local_entity_to_domain_entity.LocalEntityToDomainEntityMapper
import com.blogspot.soyamr.recipes2.data.common.mappers.local_entity_to_domain_entity.toDomain
import com.blogspot.soyamr.recipes2.data.common.util.toQueryString
import com.blogspot.soyamr.recipes2.data.database.dao.RecipeDao
import com.blogspot.soyamr.recipes2.data.database.dao.RecipeDetailedInfoDao
import com.blogspot.soyamr.recipes2.data.database.managers.CacheManager
import com.blogspot.soyamr.recipes2.data.network.RecipeApi
import com.blogspot.soyamr.recipes2.domain.RecipeRepository
import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import com.blogspot.soyamr.recipes2.domain.entities.model.RecipeDetailedInfo
import javax.inject.Inject
import kotlin.Result.Companion.success


class RepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val detailedInfoDao: RecipeDetailedInfoDao,
    private val api: RecipeApi,
    private val cacheManager: CacheManager,
    private val localEntityToDomainEntityMapper: LocalEntityToDomainEntityMapper
) :
    RecipeRepository {

    override suspend fun getRecipes(keyWord: String): Result<List<Recipe>> {
        if (recipeDao.getCount() == 0) {
            updateRecipesEntitiesFromServer()
        }
        return success(recipeDao.queryRecipes(keyWord.toQueryString()).map { it.toDomain() })
    }

    override suspend fun getRecipeDetails(id: String): Result<RecipeDetailedInfo> =
        with(localEntityToDomainEntityMapper) {
            if (detailedInfoDao.findRecipeById(id) == null) {
                api.getRecipeDetailedInfo(id).run {
                    with(cacheManager) {
                        recipe.cache()
                    }
                }
            }
            success(detailedInfoDao.findRecipeById(id)!!.toDomain())
        }

    override suspend fun updateRecipes(): Result<Unit> {
        //todo clear database
        updateRecipesEntitiesFromServer()
        return success(Unit)
    }

    private suspend fun updateRecipesEntitiesFromServer() {
        api.getRecipes().run {
            with(cacheManager) {
                recipes.map { it.cache() }
            }
        }
    }
}

