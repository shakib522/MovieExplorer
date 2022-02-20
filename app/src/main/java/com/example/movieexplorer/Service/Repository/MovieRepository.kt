package com.example.movieexplorer.Service.Repository

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.MutableLiveData
import com.example.movieexplorer.Service.Model.PopularModel.PopularMovieModel
import com.example.movieexplorer.Service.Model.PopularModel.PopularResult
import com.example.movieexplorer.Service.Model.TopModel.Result
import com.example.movieexplorer.Service.Model.TopModel.TopMovieModel
import com.example.movieexplorer.Service.Model.TrendingModel.TrendingModel
import com.example.movieexplorer.Service.Model.TrendingModel.TrendingResult
import com.example.movieexplorer.Service.Model.UpcomingModel.UpcomingModel
import com.example.movieexplorer.Service.Model.UpcomingModel.UpcomingResult
import com.example.movieexplorer.Service.Network.ApiService
import com.example.movieexplorer.Service.Network.RetrofitInstance
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

object MovieRepository {

    private lateinit var instance: MovieRepository
    private var popularResult: List<PopularResult>? = null
    private lateinit var popularLiveData: MutableLiveData<List<PopularResult>>
    private var popularMovieModel: PopularMovieModel? = null
    var apiService: ApiService? = null

    private var topMovieModel: TopMovieModel? = null
    private var topResult: List<Result>? = null
    private var topLiveData: MutableLiveData<List<Result>>?=null

    private var upcomingMovieModel: UpcomingModel? = null
    private var upcomingResult: List<UpcomingResult>? = null
    private lateinit var upcomingLiveData: MutableLiveData<List<UpcomingResult>>

    private var trendingModel: TrendingModel? = null
    private var trendingResult: List<TrendingResult>? = null
    private lateinit var trendingLiveData: MutableLiveData<List<TrendingResult>>

    fun getInstance(): MovieRepository {
        instance = MovieRepository
        return instance
    }

    suspend fun getPopularMovieList(page: Int): MutableLiveData<List<PopularResult>> {
        popularLiveData = MutableLiveData()
        //apiService = RetrofitInstance.getRetrofitInstance()?.create(ApiService::class.java)
        val popularResponse = RetrofitInstance.api.getPopularMovieList(page)
        if (popularResponse.isSuccessful && popularResponse.body() != null) {
            popularMovieModel = popularResponse.body()
            popularResult = popularMovieModel?.results
        }
        popularLiveData.postValue(popularResult)
        return popularLiveData
    }

    fun getTopMovieList(page: Int): MutableLiveData<List<Result>> ? {
        if(topLiveData==null){
            topLiveData = MutableLiveData()
            fetchTopMovieList(page)
        }
        return topLiveData
    }
    fun fetchTopMovieList(page:Int): MutableLiveData<List<Result>> ?{
        CoroutineScope(IO).launch {
            val topResponse = RetrofitInstance.api.getTopMovieList(page)
            if (topResponse.isSuccessful && topResponse.body() != null) {
                topMovieModel = topResponse.body()
                topResult = topMovieModel?.results
            }
            topLiveData?.postValue(topResult)
        }
        return topLiveData
    }

    fun getUpcomingMovieList(page: Int): MutableLiveData<List<UpcomingResult>> {
        upcomingLiveData = MutableLiveData()
        CoroutineScope(IO).launch {
            val upcomingResponse = RetrofitInstance.api.getUpcomingMovieList(page)
            if (upcomingResponse.isSuccessful && upcomingResponse.body() != null) {
                upcomingMovieModel = upcomingResponse.body()
                upcomingResult = upcomingMovieModel?.upcomingResults
            }
            upcomingLiveData.postValue(upcomingResult)
        }
        return upcomingLiveData
    }

    fun getTrending(
        type: String,
        apiKey: String,
        page: Int
    ): MutableLiveData<List<TrendingResult>> {
        trendingLiveData = MutableLiveData()
        CoroutineScope(IO).launch {
            val trendingResponse = RetrofitInstance.api.getTrendingMovie(type, apiKey, page)
            if (trendingResponse.isSuccessful && trendingResponse.body() != null) {
                trendingModel = trendingResponse.body()
                trendingResult = trendingModel?.trendingResults
            }
            trendingLiveData.postValue(trendingResult)
        }
        return trendingLiveData
    }

}