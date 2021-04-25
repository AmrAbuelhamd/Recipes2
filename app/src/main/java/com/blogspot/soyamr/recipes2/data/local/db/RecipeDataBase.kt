package com.blogspot.soyamr.recipes2.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blogspot.soyamr.recipes2.data.local.db.dao.RecipeDao
import com.blogspot.soyamr.recipes2.data.local.db.dao.RecipeDetailedInfoDao
import com.blogspot.soyamr.recipes2.data.local.db.dao.ShortRecipeInfoDao
import com.blogspot.soyamr.recipes2.data.local.db.entities.RecipeDetailedInfoEntity
import com.blogspot.soyamr.recipes2.data.local.db.entities.RecipeEntity
import com.blogspot.soyamr.recipes2.data.local.db.entities.ShortRecipeEntity


@Database(
    entities = [RecipeEntity::class, RecipeDetailedInfoEntity::class, ShortRecipeEntity::class],
    version = 4
)
abstract class RecipeDataBase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeDetailedInfoDao() : RecipeDetailedInfoDao
    abstract fun shortRecipeDao(): ShortRecipeInfoDao
}