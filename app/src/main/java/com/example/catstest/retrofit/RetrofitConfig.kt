package com.example.catstest.retrofit

import com.example.catstest.interfaces.IRest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.imgur.com/3/gallery/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun iRest(): IRest = retrofit.create(IRest::class.java)
}