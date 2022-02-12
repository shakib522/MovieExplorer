package com.example.movieexplorer.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.movieexplorer.Service.Model.PopularModel.PopularResult
import com.example.movieexplorer.Service.Model.TopModel.Result
import com.example.movieexplorer.Service.Model.TrendingModel.TrendingResult
import com.example.movieexplorer.Service.Model.UpcomingModel.UpcomingResult
import com.example.movieexplorer.Service.Repository.MovieRepository

class MovieViewModel(application:Application) : AndroidViewModel(application) {

    private var movieRepo:MovieRepository = MovieRepository.getInstance()


    fun getPopularMovieList(page:Int):MutableLiveData<List<PopularResult>>{
        return movieRepo.getPopularMovieList(page)
    }

    fun getTopMovieList(page:Int):MutableLiveData<List<Result>>{
        return movieRepo.getTopMovieList(page)
    }
    fun getUpcomingMovieList(page:Int):MutableLiveData<List<UpcomingResult>>{
        return movieRepo.getUpcomingMovieList(page)
    }

    fun getTrending(type:String,apiKey:String,page:Int):MutableLiveData<List<TrendingResult>>{
        return movieRepo.getTrending(type,apiKey,page)
    }

}