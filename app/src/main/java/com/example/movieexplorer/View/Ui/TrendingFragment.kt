package com.example.movieexplorer.View.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieexplorer.R
import com.example.movieexplorer.View.Adapter.TrendingAdapter
import com.example.movieexplorer.ViewModel.MovieViewModel
import com.google.android.material.button.MaterialButton


class TrendingFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var trendRecycler:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_trending, container, false)
        movieViewModel=ViewModelProvider(this).get(MovieViewModel::class.java)
        trendRecycler=view.findViewById(R.id.trendRecycler)
        val apiKey="61691e4870c885febcaa4652a2aa95a7"
        var page=1
        val dayWeekButton:MaterialButton=view.findViewById(R.id.dayWeekButton)
        val pageButton:MaterialButton=view.findViewById(R.id.pageButton)

        loadUpcomingMovieList(view,page,apiKey,dayWeekButton.text.toString().lowercase())

        pageButton.setOnClickListener {
            page++
            loadUpcomingMovieList(view,page,apiKey,dayWeekButton.text.toString().lowercase())
        }

        dayWeekButton.setOnClickListener {
            if(dayWeekButton.text.toString().lowercase() == "day"){
                dayWeekButton.text=getString(R.string.week_for_api)
                loadUpcomingMovieList(view,page,apiKey,dayWeekButton.text.toString().lowercase())
            }else{
                dayWeekButton.text=getString(R.string.day_for_api)
                loadUpcomingMovieList(view,page,apiKey,dayWeekButton.text.toString().lowercase())
            }
        }

        return view
    }

    private fun loadUpcomingMovieList(view:View,page: Int,apiKey:String,type:String){
        movieViewModel.getTrending(type,apiKey,page).observe(viewLifecycleOwner) {
            val trendAdapter = TrendingAdapter(view.context, it)
            trendRecycler.adapter = trendAdapter
            val manager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            trendRecycler.layoutManager = manager
        }
    }

}

