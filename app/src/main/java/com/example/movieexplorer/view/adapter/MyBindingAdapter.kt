package com.example.movieexplorer.view.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("android:loadImage")
fun ImageView.loadImage(poster:String){
    Glide.with(context).load("https://image.tmdb.org/t/p/w500/$poster").into(this)
}