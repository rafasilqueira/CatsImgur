package com.example.catstest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catstest.R
import com.example.catstest.adapter.PicturesAdapter
import com.example.catstest.model.ImgurResponse
import com.example.catstest.model.PictureInfo
import com.example.catstest.retrofit.RetrofitConfig
import com.example.catstest.utils.CallBackAPIResponse
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        const val AUTHORIZATION = "Client-ID 04e2c49522b2562"
        const val THUMBNAILMEDIUM = "m"
    }

    //After add the "m" character before file extensions , achieve the Medium thumbnail URL
    private fun getMediumThumbnailURL(originalLink: String): String { // Create a new string
        var newString = ""

        val index = originalLink.length - 4

        for (i in originalLink.indices) {
            newString += originalLink[i]

            if (i == index - 1) newString += THUMBNAILMEDIUM

        }

        return newString
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        loadData()
    }

    private fun loadData() {
        RetrofitConfig().iRest().getCats(AUTHORIZATION)
            .enqueue(object : CallBackAPIResponse<ImgurResponse>(this, true, true) {
                override fun onSuccessfulResponse(response: ImgurResponse) {
                    setupAdapter(response.data)
                }
            })
    }

    private fun setupAdapter(pictureInfoList: MutableList<PictureInfo>) {
        val picturesList = pictureInfoList.flatMap { it.images }
            .filter { it.link.isNotEmpty() }
            .filter { it.type == "image/jpeg" }
            .toMutableList()

        picturesList.forEach { it.link = getMediumThumbnailURL(it.link) }
        val picturesAdapter = PicturesAdapter(this, picturesList)
        recyclerView.adapter = picturesAdapter
    }
}
