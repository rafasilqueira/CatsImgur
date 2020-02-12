package com.example.catstest.di

import com.example.catstest.BuildConfig
import com.example.catstest.api.ImgurAPI
import com.example.catstest.service.ImgurService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

const val BASE_URL = "https://api.imgur.com/3/gallery/"

@Module
class ApiModule {

    private val loggingInterceptor = HttpLoggingInterceptor()

    private val requestInterceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Client-ID 04e2c49522b2562")
            .addHeader("FullObject", "1")
            .addHeader("Timezone", TimeZone.getDefault().id)
            .build()
        chain.proceed(newRequest)
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .addInterceptor(loggingInterceptor).build()

    init {
        loggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    fun providesImgurApi(): ImgurAPI {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ImgurAPI::class.java)
    }

    @Provides
    fun providesImgurnService(): ImgurService =
        ImgurService()
}