package com.blogspot.soyamr.recipes2.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.blogspot.soyamr.recipes2.data.local.db.entities.RecipeDetailedInfoEntity


@Dao
interface RecipeDetailedInfoDao : BaseDao<RecipeDetailedInfoEntity> {

    @Query("SELECT * FROM recipedetailedinfoentity WHERE uuid like :recipeId")
    fun findRecipeById(recipeId: String): RecipeDetailedInfoEntity?

    @Query("SELECT COUNT(*) FROM recipedetailedinfoentity")
    fun getCount(): Int

}