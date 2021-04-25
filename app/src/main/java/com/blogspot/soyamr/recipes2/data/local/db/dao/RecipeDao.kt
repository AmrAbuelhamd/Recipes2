package com.blogspot.soyamr.recipes2.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.blogspot.soyamr.recipes2.data.local.db.entities.RecipeEntity


@Dao
interface RecipeDao : BaseDao<RecipeEntity> {

    @Query("SELECT * FROM recipeentity WHERE uuid like :recipeId")
    fun findRecipeById(recipeId: String): RecipeEntity

    @Query("SELECT COUNT(*) FROM recipeentity")
    fun getCount(): Int

    @Query(
        "SELECT * FROM recipeentity where LOWER(name) like :keyword or LOWER(description) like :keyword or LOWER(instructions) like :keyword"
    )
    fun queryRecipes(keyword: String): List<RecipeEntity>
}