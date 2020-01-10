package com.example.catstest.interfaces

import com.example.catstest.model.ImgurResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface IRest {

    @GET("search/?q_type=jpg&q_size_px=small&q_size_mpx=small&q=cats")
    fun getCats(@Header("Authorization") token: String): Call<ImgurResponse>

}