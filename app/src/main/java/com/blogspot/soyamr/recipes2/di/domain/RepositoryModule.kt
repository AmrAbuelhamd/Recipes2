package com.blogspot.soyamr.recipes2.di.domain

import com.blogspot.soyamr.recipes2.data.common.domain_implementation.RecipesRepositoryImpl
import com.blogspot.soyamr.recipes2.data.common.domain_implementation.SingleRecipeRepositoryImpl
import com.blogspot.soyamr.recipes2.domain.RepositoriesContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSingleRecipeDetailsRepository(
        singleRecipeRepository: SingleRecipeRepositoryImpl
    ): RepositoriesContract.SingleRecipeRepository

    @Binds
    @Singleton
    abstract fun bindRecipesRepository(
        recipesRepository: RecipesRepositoryImpl
    ): RepositoriesContract.RecipesRepository


}
