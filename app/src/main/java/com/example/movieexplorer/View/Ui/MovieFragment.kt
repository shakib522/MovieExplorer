package com.example.movieexplorer.View.Ui

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
import com.example.movieexplorer.Service.Model.TopModel.Result
import com.example.movieexplorer.View.Adapter.PopularAdapter
import com.example.movieexplorer.View.Adapter.TopAdapter
import com.example.movieexplorer.View.Adapter.UpcomingAdapter
import com.example.movieexplorer.ViewModel.MovieViewModel
import com.google.android.material.button.MaterialButton

private const val TAG = "MovieFragment"

class MovieFragment : Fragment() {


    private lateinit var movieViewModel: MovieViewModel
    private lateinit var popularRecyclerView: RecyclerView
    private lateinit var topRecyclerView: RecyclerView
    private lateinit var upcomingRecycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        Log.d(TAG, "onCreateView: ")
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


        loadPopularMovieList(view, popularPage)
        loadTopMovieList(view)
        loadUpcomingMovieList(view, upcomingPage)
        popularButton.setOnClickListener {
            popularPage++
            fetchPopularMovieList(it, popularPage)
        }

        topButton.setOnClickListener {
            topPage++
            loadTopMovieList(it,topPage)
        }

        upcomingButton.setOnClickListener {
            upcomingPage++
            loadUpcomingMovieList(it, upcomingPage)
        }

        return view
    }

    private fun fetchPopularMovieList(view: View, page: Int) {
        movieViewModel.fetchPopularMovie(page)?.observe(viewLifecycleOwner) {
            val adapter = PopularAdapter(view.context, it)
            popularRecyclerView.adapter = adapter
            val manager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            popularRecyclerView.layoutManager = manager
        }
    }

    private fun loadPopularMovieList(view: View, page: Int) {
        movieViewModel.getPopularMovieList(page)?.observe(viewLifecycleOwner) {
            val adapter = PopularAdapter(view.context, it)
            popularRecyclerView.adapter = adapter
            val manager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            popularRecyclerView.layoutManager = manager
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

    private fun loadUpcomingMovieList(view: View, page: Int) {
        movieViewModel.getUpcomingMovieList(page).observe(viewLifecycleOwner) {
            val upcomingAdapter = UpcomingAdapter(view.context, it)
            upcomingRecycler.adapter = upcomingAdapter
            val upcomingManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            upcomingRecycler.layoutManager = upcomingManager
        }
    }

}