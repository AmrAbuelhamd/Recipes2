package com.blogspot.soyamr.recipes2.data

import com.blogspot.soyamr.recipes2.data.database.dao.RecipeDao
import com.blogspot.soyamr.recipes2.data.network.RecipeApi
import com.blogspot.soyamr.recipes2.data.util.*
import com.blogspot.soyamr.recipes2.domain.Repository
import com.blogspot.soyamr.recipes2.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import com.blogspot.soyamr.recipes2.data.network.model.Recipe as dbRecipe


class RepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val api: RecipeApi,
    private val connectivity: Connectivity
) :
    Repository {


    //get flow/observable data from database
    override fun getRecipes(): Flow<List<RecipeInfo>> =
        recipeDao.getAll().map { list -> list.map { it.toDomainRecipeInfo() } }

    //update database if it's empty only
    override suspend fun updateRecipes(): Result<Unit> =
        withContext(Dispatchers.IO) {
            val rowsInDataBase = recipeDao.getCount()
            if (rowsInDataBase <= 0) {
                return@withContext updateDataBaseFromApi()
            } else {
                return@withContext Success(Unit)
            }
        }

    override suspend fun getRecipeDetails(id: String) =
        withContext(Dispatchers.IO) {
            Success(recipeDao.findRecipeById(id).toDomainDetailedRecipeInfo())
        }

    override fun queryRecipes(keyWord: String): Flow<List<RecipeInfo>> =
        recipeDao.queryRecipes("%$keyWord%").map { list -> list.map { it.toDomainRecipeInfo() } }



    private suspend fun updateDataBaseFromApi(): Result<Unit> {
        if (connectivity.hasNetworkAccess()) {
            val result = try {
                api.getRecipes()
            } catch (e: Exception) {
                return Failure(HttpError(Throwable(e.message)))
            }
            insertDataIntoDataBase(result.recipes)
            return Success(Unit)
        } else {
            return Failure(HttpError(Throwable(NO_INTERNET_CONNECTION)))
        }
    }


    private fun insertDataIntoDataBase(finalResult: List<dbRecipe>) {
        recipeDao.deleteAll()
        recipeDao.insertAll(*finalResult.map { it.toDataBaseRecipe() }.toTypedArray())
    }
}
