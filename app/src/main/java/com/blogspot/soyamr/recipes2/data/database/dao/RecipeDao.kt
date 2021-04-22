package com.blogspot.soyamr.recipes2.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blogspot.soyamr.recipes2.data.database.model.RecipeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDao : BaseDao<RecipeEntity> {

    @Query("SELECT * FROM recipeentity WHERE uuid like :recipeId")
    fun findRecipeById(recipeId: String): RecipeEntity

    @Query("SELECT COUNT(*) FROM recipeentity")
    fun getCount(): Int

    @Query(
        "SELECT * FROM recipeentity " +
                "where LOWER(name) like :keyword " +
                "or LOWER(description) like :keyword " +
                "or LOWER(instructions) like :keyword " +
                "ORDER BY :sortType"
    )
    fun queryRecipes(sortType: String, keyword: String): List<RecipeEntity>
}