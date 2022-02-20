package com.example.movieexplorer.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieexplorer.service.model.popularModel.PopularMovieModel
import com.example.movieexplorer.service.model.popularModel.PopularResult
import com.example.movieexplorer.service.model.topModel.Result
import com.example.movieexplorer.service.model.topModel.TopMovieModel
import com.example.movieexplorer.service.model.trendingModel.TrendingModel
import com.example.movieexplorer.service.model.trendingModel.TrendingResult
import com.example.movieexplorer.service.model.upcomingModel.UpcomingModel
import com.example.movieexplorer.service.model.upcomingModel.UpcomingResult
import com.example.movieexplorer.service.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MovieViewModel : ViewModel() {

    //for popular movie
    private var popularResult: List<PopularResult>? = null
    private var popularLiveData: MutableLiveData<List<PopularResult>>? = null
    private var popularMovieModel: PopularMovieModel? = null

    //for top movie
    private var topMovieModel: TopMovieModel? = null
    private var topResult: List<Result>? = null
    private var topLiveData: MutableLiveData<List<Result>>? = null


    private var upcomingMovieModel: UpcomingModel? = null
    private var upcomingResult: List<UpcomingResult>? = null
    private var upcomingLiveData: MutableLiveData<List<UpcomingResult>>? = null

    //for trending
    private var trendingModel: TrendingModel? = null
    private var trendingResult: List<TrendingResult>? = null
    private var trendingLiveData: MutableLiveData<List<TrendingResult>>? = null


    fun getPopularMovieList(page: Int = 1): MutableLiveData<List<PopularResult>>? {
        Log.d("getPopularMovieList", "getPopularMovieList in viewmodel")
        if (page == 1) {
            popularLiveData = getPopular(page)
        } else {
            popularLiveData = fetchPopular(page)
        }
        return popularLiveData
    }

    fun getTopMovieList(page: Int = 1): MutableLiveData<List<Result>>? {
        if (page == 1) {
            topLiveData = getTop(page)
        } else {
            topLiveData = fetchTop(page)
        }
        return topLiveData
    }

    fun getUpcomingMovieList(page: Int = 1): MutableLiveData<List<UpcomingResult>>? {
        if (page == 1) {
            upcomingLiveData = getUpcoming(page)
        } else {
            upcomingLiveData = fetchUpcoming(page)
        }
        return upcomingLiveData
    }

    fun getTrendingMovieList(
        type: String,
        apiKey: String,
        page: Int
    ): MutableLiveData<List<TrendingResult>>? {
        if (page == 1) {
            trendingLiveData = getTrending(type, apiKey, page)
        } else {
            trendingLiveData = fetchTrending(type, apiKey, page)
        }
        return trendingLiveData
    }


    //worker function

    private fun getPopular(page: Int): MutableLiveData<List<PopularResult>>? {
        if (popularLiveData == null) {
            popularLiveData = MutableLiveData()
            fetchPopular(page)
        }
        return popularLiveData
    }

    private fun fetchPopular(page: Int): MutableLiveData<List<PopularResult>>? {
        viewModelScope.launch(Dispatchers.IO) {

            val popularResponse = RetrofitInstance.api.getPopularMovieList(page)
            if (popularResponse.isSuccessful && popularResponse.body() != null) {
                popularMovieModel = popularResponse.body()
                popularResult = popularMovieModel?.results
            }
            popularLiveData?.postValue(popularResult)
        }
        return popularLiveData
    }

    private fun getTop(page: Int): MutableLiveData<List<Result>>? {
        if (topLiveData == null) {
            topLiveData = MutableLiveData()
            fetchTop(page)
        }
        return topLiveData
    }

    private fun fetchTop(page: Int): MutableLiveData<List<Result>>? {
        viewModelScope.launch(Dispatchers.IO) {
            val topResponse = RetrofitInstance.api.getTopMovieList(page)
            if (topResponse.isSuccessful && topResponse.body() != null) {
                topMovieModel = topResponse.body()
                topResult = topMovieModel?.results
            }
            topLiveData?.postValue(topResult)
        }
        return topLiveData
    }


    private fun getUpcoming(page: Int): MutableLiveData<List<UpcomingResult>>? {
        if (upcomingLiveData == null) {
            upcomingLiveData = MutableLiveData()
            fetchUpcoming(page)
        }
        return upcomingLiveData
    }

    private fun fetchUpcoming(page: Int): MutableLiveData<List<UpcomingResult>>? {
        viewModelScope.launch(Dispatchers.IO) {
            val upcomingResponse = RetrofitInstance.api.getUpcomingMovieList(page)
            if (upcomingResponse.isSuccessful && upcomingResponse.body() != null) {
                upcomingMovieModel = upcomingResponse.body()
                upcomingResult = upcomingMovieModel?.upcomingResults
            }
            upcomingLiveData?.postValue(upcomingResult)
        }
        return upcomingLiveData
    }


    private fun getTrending(
        type: String,
        apiKey: String,
        page: Int
    ): MutableLiveData<List<TrendingResult>>? {
        if (trendingLiveData == null) {
            trendingLiveData = MutableLiveData()
            fetchTrending(type, apiKey, page)
        }
        return trendingLiveData
    }

    private fun fetchTrending(
        type: String,
        apiKey: String,
        page: Int
    ): MutableLiveData<List<TrendingResult>>? {
        viewModelScope.launch(Dispatchers.IO) {
            val trendingResponse = RetrofitInstance.api.getTrendingMovie(type, apiKey, page)
            if (trendingResponse.isSuccessful && trendingResponse.body() != null) {
                trendingModel = trendingResponse.body()
                trendingResult = trendingModel?.trendingResults
            }
            trendingLiveData?.postValue(trendingResult)
        }
        return trendingLiveData
    }

}