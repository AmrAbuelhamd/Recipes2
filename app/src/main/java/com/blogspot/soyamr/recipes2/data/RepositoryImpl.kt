package com.blogspot.soyamr.recipes2.data

import com.blogspot.soyamr.recipes2.data.database.dao.RecipeDao
import com.blogspot.soyamr.recipes2.data.database.dao.RecipeDetailedInfoDao
import com.blogspot.soyamr.recipes2.data.mappers.local_entity_to_domain_entity.toDomain
import com.blogspot.soyamr.recipes2.data.mappers.response_to_local_entity.toLocalEntity
import com.blogspot.soyamr.recipes2.data.network.RecipeApi
import com.blogspot.soyamr.recipes2.data.util.toQueryString
import com.blogspot.soyamr.recipes2.domain.RecipeRepository
import com.blogspot.soyamr.recipes2.domain.entities.*
import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import com.blogspot.soyamr.recipes2.domain.entities.model.RecipeDetailedInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val detailedInfoDao: RecipeDetailedInfoDao,
    private val api: RecipeApi
) :
    RecipeRepository {

    override suspend fun getRecipes(keyWord: String): Result<List<Recipe>> =
        withContext(Dispatchers.IO) {
            if (recipeDao.getCount() == 0) {
                try {
                    updateRecipesEntitiesFromServer()
                } catch (e: Exception) {
                    Failure(HttpError(Throwable(e.message)))
                }
            }
            Success(recipeDao.queryRecipes(keyWord.toQueryString()).map { it.toDomain() })
        }

    override suspend fun getRecipeDetails(id: String): Result<RecipeDetailedInfo> {
        TODO("Not yet implemented")
    }

    private suspend fun updateRecipesEntitiesFromServer() {
        val result = api.getRecipes()
        recipeDao.insertAll(result.recipes.map { it.toLocalEntity() })
    }


    override suspend fun updateRecipes(): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                updateRecipesEntitiesFromServer()
            } catch (e: Exception) {
                return@withContext Failure(HttpError(Throwable(e.message)))
            }
            return@withContext Success(Unit)
        }
}

