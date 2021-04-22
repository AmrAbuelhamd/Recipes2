package com.blogspot.soyamr.recipes2.di.data

import com.blogspot.soyamr.recipes2.data.common.util.Connectivity
import com.blogspot.soyamr.recipes2.data.common.util.ConnectivityImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectivityModule {
    @Binds
    @Singleton
    abstract fun getConnectivityImpl(connectivityImpl: ConnectivityImpl): Connectivity
}