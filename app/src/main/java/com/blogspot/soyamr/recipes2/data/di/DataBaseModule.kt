package com.blogspot.soyamr.recipes2.data.di

import android.content.Context
import androidx.room.Room
import com.blogspot.soyamr.recipes2.data.database.RecipeDataBase
import com.blogspot.soyamr.recipes2.data.database.dao.RecipeDao
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
        ).build()

    @Provides
    @Singleton
    fun getCurrencyDao(db: RecipeDataBase): RecipeDao = db.recipeDao()

}