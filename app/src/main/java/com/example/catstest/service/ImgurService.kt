package com.example.catstest.service

import com.example.catstest.di.DaggerApiComponent
import com.example.catstest.api.ImgurAPI
import javax.inject.Inject

const val AUTHORIZATION = "Client-ID 04e2c49522b2562"

class ImgurService {

    @Inject
    lateinit var imgurAPI: ImgurAPI

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getImgurResponse() = imgurAPI.getCats()
}