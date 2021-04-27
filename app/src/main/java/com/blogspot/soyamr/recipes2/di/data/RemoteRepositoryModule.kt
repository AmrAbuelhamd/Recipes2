package com.blogspot.soyamr.recipes2.di.data

import com.blogspot.soyamr.recipes2.data.common.contracts.RemoteContract
import com.blogspot.soyamr.recipes2.data.network.implementation.RecipeRepositoryImpl
import com.blogspot.soyamr.recipes2.utils.Utils
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRecipeRepository(
        recipesRepositoryImpl: RecipeRepositoryImpl
    ): RemoteContract.RecipeRepository

}