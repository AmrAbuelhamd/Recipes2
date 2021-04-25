package com.blogspot.soyamr.recipes2.di.data

import android.content.Context
import androidx.room.Room
import com.blogspot.soyamr.recipes2.data.local.db.RecipeDataBase
import com.blogspot.soyamr.recipes2.data.local.db.dao.RecipeDao
import com.blogspot.soyamr.recipes2.data.local.db.dao.RecipeDetailedInfoDao
import com.blogspot.soyamr.recipes2.data.local.db.dao.ShortRecipeInfoDao
import com.blogspot.soyamr.recipes2.data.local.managers.CacheManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun getDataBaseObject(@ApplicationContext context: Context): RecipeDataBase =
        Room.databaseBuilder(
            context,
            RecipeDataBase::class.java, "recipe-database"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun getRecipeDao(db: RecipeDataBase): RecipeDao = db.recipeDao()

    @Provides
    @Singleton
    fun getRecipeDetailedInfoDao(db: RecipeDataBase): RecipeDetailedInfoDao =
        db.recipeDetailedInfoDao()

    @Provides
    @Singleton
    fun getShortRecipeDao(db: RecipeDataBase): ShortRecipeInfoDao = db.shortRecipeDao()

    @Provides
    @Singleton
    fun getCacheManager(
        recipeDao: RecipeDao,
        recipeDetailedInfoDao: RecipeDetailedInfoDao,
        shortRecipeInfoDao: ShortRecipeInfoDao
    ): CacheManager = CacheManager(recipeDao, recipeDetailedInfoDao, shortRecipeInfoDao)

}