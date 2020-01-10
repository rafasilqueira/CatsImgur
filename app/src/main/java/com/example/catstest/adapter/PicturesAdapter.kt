package com.example.catstest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.catstest.R
import com.example.catstest.model.Images
import com.squareup.picasso.Picasso

class PicturesAdapter(context: Context, pictureInfoList: MutableList<Images>) : AbstractAdapter<Images>(context, pictureInfoList) {

    override fun setupViewHolder(parent: ViewGroup, viewType: Int): VHPictures {
        return VHPictures(
            LayoutInflater.from(context).inflate(
                R.layout.list_item_pictures,
                parent,
                false
            )
        )
    }

    override fun onBindData(holder: RecyclerView.ViewHolder, genericType: Images) {
        holder.setIsRecyclable(false)
        if (holder is VHPictures) {
            Picasso.with(context).load(genericType.link)
                .placeholder(R.drawable.ic_image_black_24dp)
                .into(holder.imgPicture)

            holder.itemView.setOnClickListener {
                genericType.description?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}