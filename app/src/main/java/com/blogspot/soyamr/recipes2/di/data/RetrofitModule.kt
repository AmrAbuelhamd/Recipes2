package com.blogspot.soyamr.recipes2.di.data

import com.blogspot.soyamr.recipes2.data.common.contracts.RemoteContract
import com.blogspot.soyamr.recipes2.data.network.RecipeApi
import com.blogspot.soyamr.recipes2.utils.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Singleton


private const val BASE_URL = "https://test.kode-t.ru/"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun getRetrofitObject(utilsRepository: RemoteContract.UtilsRepository): RecipeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                ).addInterceptor { chain ->
                    val request = chain.request()
                    if (!utilsRepository.hasInternetConnection()) {
                        throw IOException(Constants.NO_INTERNET_CONNECTION)
                    }
                    chain.proceed(request)
                }.build()
            )
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                }.asConverterFactory("application/json".toMediaType())
            )
            .build().create(RecipeApi::class.java)
    }
}