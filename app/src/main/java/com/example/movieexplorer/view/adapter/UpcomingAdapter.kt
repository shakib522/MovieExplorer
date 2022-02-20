package com.example.movieexplorer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieexplorer.databinding.SingleRowUpcomingBinding
import com.example.movieexplorer.service.model.upcomingModel.UpcomingResult



class UpcomingAdapter(private val context: Context, private val movieList: List<UpcomingResult>) :
    RecyclerView.Adapter<UpcomingAdapter.UpcomingMyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = SingleRowUpcomingBinding.inflate(inflater, parent, false)
        return UpcomingMyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (movieList.isEmpty()) {
            return 0
        }
        return movieList.size
    }
    override fun onBindViewHolder(holder: UpcomingMyViewHolder, position: Int) {
        holder.binding.upcoming = movieList[position]
        holder.binding.progressBarId.progress=movieList[position].voteAverage.toInt()
        holder.binding.executePendingBindings()

    }
    inner class UpcomingMyViewHolder(val binding: SingleRowUpcomingBinding) :
        RecyclerView.ViewHolder(binding.root)
}

