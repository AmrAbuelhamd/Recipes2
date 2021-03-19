package com.blogspot.soyamr.recipes2.domain.di

import com.blogspot.soyamr.recipes2.domain.interactors.*
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
    abstract fun bindUseCase1(getRecipeDetails: GetRecipeDetailedInfoUseCaseImpl): GetRecipeDetailedInfoUseCase

    @Binds
    @Singleton
    abstract fun bindUseCase2(getRecipes: GetRecipesListUseCaseImpl): GetRecipesListUseCase

    @Binds
    @Singleton
    abstract fun bindUseCase3(updateRecipes: UpdateRecipesUseCaseImpl): UpdateRecipesUseCase

    @Binds
    @Singleton
    abstract fun bindUseCase4(queryRecipes: QueryRecipesImpl): QueryRecipes

}