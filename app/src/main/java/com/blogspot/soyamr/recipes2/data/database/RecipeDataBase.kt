package com.blogspot.soyamr.recipes2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blogspot.soyamr.recipes2.data.database.dao.RecipeDao
import com.blogspot.soyamr.recipes2.data.database.dao.RecipeDetailedInfoDao
import com.blogspot.soyamr.recipes2.data.database.dao.ShortRecipeInfoDao
import com.blogspot.soyamr.recipes2.data.database.model.RecipeDetailedInfoEntity
import com.blogspot.soyamr.recipes2.data.database.model.RecipeEntity
import com.blogspot.soyamr.recipes2.data.database.model.ShortRecipeEntity


@Database(
    entities = [RecipeEntity::class, RecipeDetailedInfoEntity::class, ShortRecipeEntity::class],
    version = 4
)
abstract class RecipeDataBase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeDetailedInfoDao() : RecipeDetailedInfoDao
    abstract fun shortRecipeDao(): ShortRecipeInfoDao
}