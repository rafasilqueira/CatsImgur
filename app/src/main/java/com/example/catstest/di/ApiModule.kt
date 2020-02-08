package com.example.catstest.di

import com.example.catstest.model.ImgurAPI
import com.example.catstest.model.ImgurService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.imgur.com/3/gallery/"

@Module
class ApiModule {

    @Provides
    fun providesImgurApi(): ImgurAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ImgurAPI::class.java)

    @Provides
    fun providesImgurnService(): ImgurService = ImgurService()
}