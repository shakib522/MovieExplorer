package com.example.movieexplorer.View.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieexplorer.R
import com.example.movieexplorer.Service.Model.TopModel.Result

class TopAdapter(private val context:Context, private val movieList:List<Result>) : RecyclerView.Adapter<TopAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAdapter.MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.single_row_picture,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopAdapter.MyViewHolder, position: Int) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+ movieList[position].posterPath).into(holder.imageView)
    }

    override fun getItemCount(): Int {
      return movieList.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView:ImageView = itemView.findViewById(R.id.popularTopImageViewId)
    }
}