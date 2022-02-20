package com.example.movieexplorer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieexplorer.service.model.popularModel.PopularResult
import com.example.movieexplorer.databinding.SingleRowPictureBinding

class PopularAdapter(private val context:Context, private val movieList:List<PopularResult>) : RecyclerView.Adapter<PopularAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.MyViewHolder {
        val inflater=LayoutInflater.from(context)
        val itemBinding=SingleRowPictureBinding.inflate(inflater,parent,false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.MyViewHolder, position: Int) {
        val popularResult= movieList[position]
        holder.itemBinding.popular=popularResult
        holder.itemBinding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        if(movieList.isEmpty()){
            return 0
        }
        return movieList.size
    }


    inner class MyViewHolder(val itemBinding:SingleRowPictureBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        //var imageView:ImageView = itemView.findViewById(R.id.popularTopImageViewId)
    }
}