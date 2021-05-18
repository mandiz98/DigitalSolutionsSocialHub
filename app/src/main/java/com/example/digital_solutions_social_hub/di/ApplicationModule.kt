package com.example.digital_solutions_social_hub.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

//    @Provides
//    fun provideBaseUrl() = TODO()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

//    @Provides
//    @Singleton
//    fun provideApiHelper(apiHelper:ApiHelperImpl): ApiHelper = apiHelper
}