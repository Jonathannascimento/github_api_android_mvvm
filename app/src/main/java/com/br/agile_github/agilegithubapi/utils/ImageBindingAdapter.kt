package com.br.agile_github.agilegithubapi.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


class ImageBindingAdapter {

    companion object {

        @BindingAdapter("bind:imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String) {
            if (url != "") {
                Picasso.with(imageView.context).load(url).transform(CropCircleTransformation()).into(imageView)
            }
        }
    }
}