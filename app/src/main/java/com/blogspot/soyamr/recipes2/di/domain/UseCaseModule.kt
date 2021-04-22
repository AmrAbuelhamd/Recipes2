package com.blogspot.soyamr.recipes2.di.domain

import com.blogspot.soyamr.recipes2.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindGetRecipeDetailsUseCase(getRecipeDetails: GetRecipeDetailedInfoUseCaseImpl): GetRecipeDetailedInfoUseCase

    @Binds
    @Singleton
    abstract fun bindGetRecipesListUseCase(getRecipes: GetRecipesListUseCaseImpl): GetRecipesListUseCase

    @Binds
    @Singleton
    abstract fun bindUpdateRecipesListUseCase(getRecipes: UpdateRecipesUseCaseImpl): UpdateRecipesUseCase

}