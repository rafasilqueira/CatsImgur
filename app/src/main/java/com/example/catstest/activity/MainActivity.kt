package com.example.catstest.activity

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.catstest.R
import com.example.catstest.adapter.PicturesAdapter
import com.example.catstest.model.ImgurResponse
import com.example.catstest.viewmodel.CatsViewModel
import kotlinx.android.synthetic.main.activity_main.*

const val THUMBNAILMEDIUM = "m"

class MainActivity : AppCompatActivity() {

    private lateinit var catsViewModel: CatsViewModel
    private val picturesAdapter = PicturesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catsViewModel = ViewModelProviders.of(this).get(CatsViewModel::class.java)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        recyclerView.adapter = picturesAdapter
        observeViewModel()
        catsViewModel.fetch()
    }

    private fun observeViewModel() {
        catsViewModel.apply {
            imgurResponse.observe(this@MainActivity, Observer { observeData(it) })
            isError.observe(this@MainActivity, Observer { observeError(it) })
            loading.observe(this@MainActivity, Observer { observerLoadingDialog(it) })
        }


    }

    private fun observerLoadingDialog(loading: Boolean) {
        loading.let { progressBar.visibility = if (it) VISIBLE else GONE }

        if (loading) {
            recyclerView.visibility = GONE
            txtError.visibility = GONE
        }
    }


    private fun observeError(isError: Boolean) {
        txtError.visibility = if (isError) VISIBLE else GONE
    }

    private fun observeData(imgurResponse: ImgurResponse) {
        recyclerView.visibility = View.VISIBLE
        val picturesList = imgurResponse.data.flatMap { pictureInfo -> pictureInfo.images }
            .filter { images -> images.link.isNotEmpty() }
            .filter { images -> images.type == "image/jpeg" }
            .toMutableList()

        picturesList.forEach { image -> image.link = getMediumThumbnailURL(image.link) }
        picturesAdapter.updateData(picturesList)
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

}
