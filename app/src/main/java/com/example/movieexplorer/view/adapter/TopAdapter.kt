package com.example.movieexplorer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieexplorer.databinding.SingleRowPictureTopMovieBinding
import com.example.movieexplorer.service.model.topModel.Result

class TopAdapter(private val context: Context, private val movieList: List<Result>) :
    RecyclerView.Adapter<TopAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAdapter.MyViewHolder {
        val inflater=LayoutInflater.from(context)
        val itemBinding=SingleRowPictureTopMovieBinding.inflate(inflater,parent,false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TopAdapter.MyViewHolder, position: Int) {
        val topResult=movieList[position]
        holder.itemBinding.top=topResult
        holder.itemBinding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        if (movieList.isEmpty()) {
            return 0
        }
        return movieList.size
    }

    inner class MyViewHolder(val itemBinding: SingleRowPictureTopMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        //var imageView:ImageView = itemView.findViewById(R.id.popularTopImageViewId)
    }
}