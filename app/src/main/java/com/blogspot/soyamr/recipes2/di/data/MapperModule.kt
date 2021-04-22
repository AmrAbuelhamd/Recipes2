package com.blogspot.soyamr.recipes2.di.data

import com.blogspot.soyamr.recipes2.data.database.dao.ShortRecipeInfoDao
import com.blogspot.soyamr.recipes2.data.mappers.response_to_local_entity.ResponseToDomainEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {
    @Provides
    @Singleton
    fun getDataBaseObject(shortRecipeInfoDao: ShortRecipeInfoDao): ResponseToDomainEntityMapper =
        ResponseToDomainEntityMapper(shortRecipeInfoDao)


}