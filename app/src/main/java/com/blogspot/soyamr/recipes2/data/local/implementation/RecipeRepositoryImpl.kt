package com.blogspot.soyamr.recipes2.data.local.implementation

import com.blogspot.soyamr.recipes2.data.common.contracts.LocaleContract
import com.blogspot.soyamr.recipes2.data.common.util.toQueryString
import com.blogspot.soyamr.recipes2.data.local.db.dao.RecipeDao
import com.blogspot.soyamr.recipes2.data.local.db.dao.RecipeDetailedInfoDao
import com.blogspot.soyamr.recipes2.data.local.db.entities.RecipeDetailedInfoEntity
import com.blogspot.soyamr.recipes2.data.local.db.entities.RecipeEntity
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val recipeDetailedInfoDao: RecipeDetailedInfoDao
) : LocaleContract.RecipeRepository {

    override suspend fun getRecipes(keyWord: String): List<RecipeEntity> =
        recipeDao.queryRecipes(keyWord.toQueryString())

    override suspend fun getRecipesCount(): Int =
        recipeDao.getCount()


    override suspend fun getRecipeDetails(id: String): RecipeDetailedInfoEntity? =
        recipeDetailedInfoDao.findRecipeById(id)


}