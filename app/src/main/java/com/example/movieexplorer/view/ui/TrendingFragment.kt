package com.example.movieexplorer.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieexplorer.R
import com.example.movieexplorer.databinding.FragmentTrendingBinding
import com.example.movieexplorer.view.adapter.TrendingAdapter
import com.example.movieexplorer.viewModel.MovieViewModel
import com.google.android.material.button.MaterialButton

private const val apiKey="61691e4870c885febcaa4652a2aa95a7"
class TrendingFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
   private lateinit var binding:FragmentTrendingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentTrendingBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_trending, container, false)
        movieViewModel=ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)

        var page=1
        loadTrending(view,apiKey,binding.dayWeekButton.text.toString().lowercase())
        binding.pageButton.setOnClickListener {
            page++
            loadTrending(view,apiKey,binding.dayWeekButton.text.toString().lowercase(),page)
        }

        binding.dayWeekButton.setOnClickListener {
            if(binding.dayWeekButton.text.toString().lowercase() == "day"){
                binding.dayWeekButton.text=getString(R.string.week_for_api)
                loadTrending(view,apiKey,"week",page)
            }else{
                binding.dayWeekButton.text=getString(R.string.day_for_api)
                loadTrending(view,apiKey,"day",page)
            }
        }

        return binding.root
    }

    private fun loadTrending(view:View,apiKey:String,type:String,page:Int=1){
        movieViewModel.getTrendingMovieList(type,apiKey,page)?.observe(viewLifecycleOwner) {
            binding.trendRecycler.adapter = TrendingAdapter(view.context, it)
            val manager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            binding.trendRecycler.layoutManager = manager
        }
    }

}

