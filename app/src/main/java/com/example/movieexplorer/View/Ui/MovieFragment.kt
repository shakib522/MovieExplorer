package com.example.movieexplorer.View.Ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieexplorer.R
import com.example.movieexplorer.View.Adapter.PopularAdapter
import com.example.movieexplorer.View.Adapter.TopAdapter
import com.example.movieexplorer.ViewModel.MovieViewModel
import com.google.android.material.button.MaterialButton


class MovieFragment : Fragment() {


    private lateinit var movieViewModel: MovieViewModel
    private lateinit var popularRecyclerView: RecyclerView
    private lateinit var topRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_movie, container, false)


        var popularPage = 1
        var topPage=1

        val popularButton = view.findViewById<MaterialButton>(R.id.popularButton)
        val topButton = view.findViewById<MaterialButton>(R.id.topButton)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        popularRecyclerView = view.findViewById<RecyclerView>(R.id.popularRecyclerId)

        topRecyclerView = view.findViewById<RecyclerView>(R.id.topRatedRecyclerId)


        loadPopularMovieList(view, popularPage)
        loadTopMovieList(view,topPage)
        popularButton.setOnClickListener {
            popularPage++
            loadPopularMovieList(it, popularPage)
        }

        topButton.setOnClickListener {
            topPage++
            loadTopMovieList(it,topPage)
        }

        return view
    }

    private fun loadPopularMovieList(view: View, page: Int) {
        movieViewModel.getPopularMovieList(page).observe(viewLifecycleOwner, Observer {
            val adapter = PopularAdapter(view.context, it)
            popularRecyclerView.adapter = adapter
            val manager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            popularRecyclerView.layoutManager = manager
        })
    }

    private fun loadTopMovieList(view: View, page: Int) {
        movieViewModel.getTopMovieList(page).observe(viewLifecycleOwner, Observer {
            val topAdapter = TopAdapter(view.context, it)
            topRecyclerView.adapter = topAdapter
            val topManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
            topRecyclerView.layoutManager = topManager
        })
    }


}