package com.blogspot.soyamr.recipes2.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.blogspot.soyamr.recipes2.data.database.model.Recipe
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe ORDER BY name")
    fun getAllOrderByName(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe ORDER BY lastUpdated")
    fun getAllOrderByDate(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe")
    fun getAllOrderDefault(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE uuid like :recipeId")
    fun findRecipeById(recipeId: String): Recipe

    @Insert
    fun insertAll(vararg recipes: Recipe)

    @Query("DELETE FROM recipe")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM recipe")
    fun getCount(): Int

    @Query("SELECT * FROM recipe " +
            "where LOWER(name) like :keyword " +
            "or LOWER(description) like :keyword " +
            "or LOWER(instructions) like :keyword")
    fun queryRecipes(keyword: String): Flow<List<Recipe>>
}