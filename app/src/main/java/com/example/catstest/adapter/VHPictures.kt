package com.example.catstest.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.catstest.R

class VHPictures(view: View) : RecyclerView.ViewHolder(view) {
    val imgPicture: AppCompatImageView = view.findViewById(R.id.img)
}