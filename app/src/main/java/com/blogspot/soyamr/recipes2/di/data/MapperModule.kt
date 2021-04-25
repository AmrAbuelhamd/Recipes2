package com.blogspot.soyamr.recipes2.di.data

import com.blogspot.soyamr.recipes2.data.common.mappers.local_entity_to_domain_entity.LocalEntityToDomainEntityMapper
import com.blogspot.soyamr.recipes2.data.local.db.dao.ShortRecipeInfoDao
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
    fun getLocalEntityToDomainMapper(shortRecipeInfoDao: ShortRecipeInfoDao): LocalEntityToDomainEntityMapper =
        LocalEntityToDomainEntityMapper(shortRecipeInfoDao)


}