package com.blogspot.soyamr.recipes2.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blogspot.soyamr.recipes2.data.database.model.RecipeEntity
import com.blogspot.soyamr.recipes2.data.database.model.ShortRecipeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ShortRecipeInfoDao : BaseDao<RecipeEntity> {

    @Query("SELECT * FROM shortrecipeentity WHERE uuid like :recipeId")
    fun findRecipeById(recipeId: String): ShortRecipeEntity

    @Query("SELECT COUNT(*) FROM shortrecipeentity")
    fun getCount(): Int

}