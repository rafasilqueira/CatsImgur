package com.example.catstest.di

import com.example.catstest.model.ImgurService
import com.example.catstest.viewmodel.CatsViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service : ImgurService)
    fun inject(catsViewModel: CatsViewModel)
}