package com.example.catstest.model

import com.example.catstest.di.DaggerApiComponent
import javax.inject.Inject

const val AUTHORIZATION = "Client-ID 04e2c49522b2562"

class ImgurService {

    @Inject
    lateinit var imgurAPI: ImgurAPI

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getImgurResponse() = imgurAPI.getCats(AUTHORIZATION)
}