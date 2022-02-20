package com.example.movieexplorer.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieexplorer.R
import com.example.movieexplorer.view.adapter.PopularAdapter
import com.example.movieexplorer.view.adapter.TopAdapter
import com.example.movieexplorer.view.adapter.UpcomingAdapter
import com.example.movieexplorer.viewModel.MovieViewModel
import com.example.movieexplorer.databinding.FragmentMovieBinding


class MovieFragment : Fragment() {

    lateinit var binding:FragmentMovieBinding
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding= FragmentMovieBinding.inflate(layoutInflater)

        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        var popularPage = 1
        var topPage = 1
        var upcomingPage = 1

        movieViewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)

        loadPopularMovieList(view)
        loadTopMovieList(view)
        loadUpcomingMovieList(view)
        binding.popularButton.setOnClickListener {
            popularPage++
            Log.d("MovieFragment", "buttonClicked $popularPage")
            loadPopularMovieList(it,popularPage)
        }

        binding.topButton.setOnClickListener {
            topPage++
            loadTopMovieList(it, topPage)
        }

        binding.upcomingButton.setOnClickListener {
            upcomingPage++
            loadUpcomingMovieList(it, upcomingPage)
        }
        return binding.root
    }


    private fun loadPopularMovieList(view: View, page: Int = 1) {
        Log.d("loadPopularMovieList", "in load Popular Movie List")
        movieViewModel.getPopularMovieList(page)?.observe(viewLifecycleOwner) {
            val adapter = PopularAdapter(view.context, it)
            binding.popularRecyclerId.adapter = adapter
            val manager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            binding.popularRecyclerId.layoutManager = manager
        }
    }

    private fun loadTopMovieList(view: View, page: Int = 1) {
        movieViewModel.getTopMovieList(page)?.observe(viewLifecycleOwner) {
            val adapter = TopAdapter(view.context, it)
            binding.topRatedRecyclerId.adapter = adapter
            val manager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            binding.topRatedRecyclerId.layoutManager = manager
        }

    }

    private fun loadUpcomingMovieList(view: View, page: Int=1) {
        movieViewModel.getUpcomingMovieList(page)?.observe(viewLifecycleOwner) {
            val upcomingAdapter = UpcomingAdapter(view.context, it)
            binding.upComingRecyclerId.adapter = upcomingAdapter
            val upcomingManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            binding.upComingRecyclerId.layoutManager = upcomingManager
        }
    }

}