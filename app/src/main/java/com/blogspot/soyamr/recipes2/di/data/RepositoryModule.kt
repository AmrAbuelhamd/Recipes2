package com.blogspot.soyamr.recipes2.di.data

import com.blogspot.soyamr.recipes2.data.RepositoryImpl
import com.blogspot.soyamr.recipes2.domain.RecipeRepository
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
    abstract fun getRepo(repositoryImpl: RepositoryImpl): RecipeRepository
}