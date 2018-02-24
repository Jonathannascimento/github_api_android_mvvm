package com.br.agile_github.agilegithubapi.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

/**
 * A group of *utils*.
 *
 * Causes all images with bind imageUrl to be tagged
 *
 */
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