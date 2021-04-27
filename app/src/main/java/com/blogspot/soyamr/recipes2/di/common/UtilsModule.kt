package com.blogspot.soyamr.recipes2.di.common

import android.content.Context
import com.blogspot.soyamr.recipes2.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {
    @Provides
    @Singleton
    fun getUtils(@ApplicationContext context: Context): Utils = Utils(context)

}
