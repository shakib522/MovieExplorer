package com.example.movieexplorer.view.Ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieexplorer.R
import com.example.movieexplorer.view.adapter.PopularAdapter
import com.example.movieexplorer.view.adapter.TopAdapter
import com.example.movieexplorer.view.adapter.UpcomingAdapter
import com.example.movieexplorer.ViewModel.MovieViewModel
import com.example.movieexplorer.databinding.FragmentMovieBinding
import com.google.android.material.button.MaterialButton
import kotlin.math.log


class MovieFragment : Fragment() {

    lateinit var binding:FragmentMovieBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var popularRecyclerView: RecyclerView
    private lateinit var topRecyclerView: RecyclerView
    private lateinit var upcomingRecycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentMovieBinding.inflate(layoutInflater)

        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        var popularPage = 1
        var topPage = 1
        var upcomingPage = 1

        val popularButton = view.findViewById<MaterialButton>(R.id.popularButton)
        val topButton = view.findViewById<MaterialButton>(R.id.topButton)
        val upcomingButton = view.findViewById<MaterialButton>(R.id.upcomingButton)
        movieViewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        popularRecyclerView = view.findViewById(R.id.popularRecyclerId)
        topRecyclerView = view.findViewById(R.id.topRatedRecyclerId)
        upcomingRecycler = view.findViewById(R.id.upComingRecyclerId)


        loadPopularMovieList(view)
        loadTopMovieList(view)
        loadUpcomingMovieList(view)
        binding.popularButton.setOnClickListener {
            popularPage++
            Log.d("MovieFragment", "buttonClicked $popularPage")
            loadPopularMovieList(it,popularPage)
        }

        topButton.setOnClickListener {
            topPage++
            loadTopMovieList(it, topPage)
        }

        upcomingButton.setOnClickListener {
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
            topRecyclerView.adapter = adapter
            val manager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            topRecyclerView.layoutManager = manager
        }

    }

    private fun loadUpcomingMovieList(view: View, page: Int=1) {
        movieViewModel.getUpcomingMovieList(page)?.observe(viewLifecycleOwner) {
            val upcomingAdapter = UpcomingAdapter(view.context, it)
            upcomingRecycler.adapter = upcomingAdapter
            val upcomingManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            upcomingRecycler.layoutManager = upcomingManager
        }
    }

}