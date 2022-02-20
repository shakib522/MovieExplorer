package com.example.movieexplorer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieexplorer.databinding.SingleRowTrendingBinding
import com.example.movieexplorer.service.model.trendingModel.TrendingResult

class TrendingAdapter(private val context: Context, private val movieList: List<TrendingResult>) :
    RecyclerView.Adapter<TrendingAdapter.TrendingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingHolder {
        val inflater=LayoutInflater.from(context)
        val itemBinding= SingleRowTrendingBinding.inflate(inflater,parent,false)
        return TrendingHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        if (movieList.isEmpty()) {
            return 0
        }
        return movieList.size
    }

    override fun onBindViewHolder(holder: TrendingHolder, position: Int) {
        holder.binding.trending=movieList[position]
        holder.binding.trendingProgressBarId.progress=movieList[position].voteAverage.toInt()
        holder.binding.executePendingBindings()
    }

    inner class TrendingHolder(val binding:SingleRowTrendingBinding) : RecyclerView.ViewHolder(binding.root)

}

