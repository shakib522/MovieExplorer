package com.example.movieexplorer.View.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieexplorer.R
import com.example.movieexplorer.Service.Model.UpcomingModel.UpcomingResult
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlin.math.roundToInt


class UpcomingAdapter(private val context: Context, private val movieList: List<UpcomingResult>) :
    RecyclerView.Adapter<UpcomingAdapter.UpcomingMyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_row_picture_text, parent, false)
        return UpcomingMyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (movieList.isEmpty()) {
            return 0
        }
        return movieList.size
    }

    override fun onBindViewHolder(holder: UpcomingMyViewHolder, position: Int) {
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/" + movieList[position].posterPath)
            .into(holder.imageView)
        holder.progressBar.progress= movieList[position].voteAverage.roundToInt()
        val title="Title : " + movieList[position].title
        val releaseDate="Release Date : " + movieList[position].releaseDate
        holder.title.text=title
        holder.releaseDate.text=releaseDate

    }

    inner class UpcomingMyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.textImageViewId)
        var progressBar: LinearProgressIndicator =itemView.findViewById(R.id.progressBarId)
        var title: TextView =itemView.findViewById(R.id.titleTvId)
        var releaseDate: TextView =itemView.findViewById(R.id.releaseDateTv)
    }

}

