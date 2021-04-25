package com.blogspot.soyamr.recipes2.di.data

import com.blogspot.soyamr.recipes2.data.common.contracts.LocaleContract
import com.blogspot.soyamr.recipes2.data.local.implementation.RecipeRepositoryImpl
import com.blogspot.soyamr.recipes2.data.local.implementation.UtilsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocaleRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUtilsRepository(
        utilsRepository: UtilsRepositoryImpl
    ): LocaleContract.UtilsRepository

    @Binds
    @Singleton
    abstract fun bindRecipeRepository(
        recipesRepositoryImpl: RecipeRepositoryImpl
    ): LocaleContract.RecipeRepository

}
