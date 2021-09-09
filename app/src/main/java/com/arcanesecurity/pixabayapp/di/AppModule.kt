package com.arcanesecurity.pixabayapp.di

import com.arcanesecurity.pixabayapp.BuildConfig
import com.arcanesecurity.pixabayapp.services.PixabayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providePixabayApi(retrofit: Retrofit): PixabayApi =
        retrofit.create(PixabayApi::class.java)


}