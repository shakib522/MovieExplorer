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


class MovieFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie, container, false)


        val popularRecyclerView = view.findViewById<RecyclerView>(R.id.popularRecyclerId)
        val manager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        popularRecyclerView.layoutManager = manager
        val movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.getPopularMovieList().observe(viewLifecycleOwner, Observer {
            val adapter = PopularAdapter(view.context, it)
            popularRecyclerView.adapter = adapter
        })

        val topRecyclerView=view.findViewById<RecyclerView>(R.id.topRatedRecyclerId)
        val topManager=StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL)
        topRecyclerView.layoutManager=topManager
        movieViewModel.getTopMovieList().observe(viewLifecycleOwner, Observer {
            val topAdapter=TopAdapter(view.context,it)
            topRecyclerView.adapter=topAdapter
        })

        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }

}