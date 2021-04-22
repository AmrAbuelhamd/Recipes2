package com.blogspot.soyamr.recipes2.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blogspot.soyamr.recipes2.data.database.model.RecipeDetailedInfoEntity
import com.blogspot.soyamr.recipes2.data.database.model.RecipeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDetailedInfoDao : BaseDao<RecipeDetailedInfoEntity> {

    @Query("SELECT * FROM recipedetailedinfoentity WHERE uuid like :recipeId")
    fun findRecipeById(recipeId: String): RecipeDetailedInfoEntity?

    @Query("SELECT COUNT(*) FROM recipedetailedinfoentity")
    fun getCount(): Int

}