package com.example.movieexplorer.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.movieexplorer.Service.Model.PopularModel.PopularResult
import com.example.movieexplorer.Service.Model.TopModel.Result
import com.example.movieexplorer.Service.Repository.MovieRepository

class MovieViewModel(application:Application) : AndroidViewModel(application) {

    private var movieRepo:MovieRepository = MovieRepository.getInstance()


    fun getPopularMovieList():MutableLiveData<List<PopularResult>>{
        return movieRepo.getPopularMovieList()
    }

    fun getTopMovieList():MutableLiveData<List<Result>>{
        return movieRepo.getTopMovieList()
    }

}