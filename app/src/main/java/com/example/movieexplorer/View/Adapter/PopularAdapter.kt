package com.example.movieexplorer.View.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieexplorer.R
import com.example.movieexplorer.Service.Model.PopularModel.PopularResult

class PopularAdapter(private val context:Context, private val movieList:List<PopularResult>) : RecyclerView.Adapter<PopularAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.MyViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.single_row_picture,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularAdapter.MyViewHolder, position: Int) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+movieList.get(position).posterPath).into(holder.imageView)
    }

    override fun getItemCount(): Int {
      return movieList.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView:ImageView = itemView.findViewById(R.id.popularTopImageViewId)
    }
}