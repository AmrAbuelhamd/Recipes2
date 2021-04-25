package com.blogspot.soyamr.recipes2.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.blogspot.soyamr.recipes2.data.local.db.entities.ShortRecipeEntity


@Dao
interface ShortRecipeInfoDao : BaseDao<ShortRecipeEntity> {

    @Query("SELECT * FROM shortrecipeentity WHERE uuid like :recipeId")
    fun findRecipeById(recipeId: String): ShortRecipeEntity

    @Query("SELECT COUNT(*) FROM shortrecipeentity")
    fun getCount(): Int

}