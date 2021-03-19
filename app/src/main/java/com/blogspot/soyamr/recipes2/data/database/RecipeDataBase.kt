package com.blogspot.soyamr.recipes2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.blogspot.soyamr.recipes2.data.database.dao.RecipeDao
import com.blogspot.soyamr.recipes2.data.database.model.Converters
import com.blogspot.soyamr.recipes2.data.database.model.Recipe


@Database(entities = [Recipe::class], version = 2)
@TypeConverters(Converters::class)
abstract class RecipeDataBase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}