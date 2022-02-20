package com.example.movieexplorer.service.network

import retrofit2.http.GET
import com.example.movieexplorer.service.model.popularModel.PopularMovieModel
import com.example.movieexplorer.service.model.topModel.TopMovieModel
import com.example.movieexplorer.service.model.trendingModel.TrendingModel
import com.example.movieexplorer.service.model.upcomingModel.UpcomingModel
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular?api_key=61691e4870c885febcaa4652a2aa95a7")
    suspend fun getPopularMovieList(@Query("page")page:Int):Response<PopularMovieModel>

    @GET("3/movie/top_rated?api_key=61691e4870c885febcaa4652a2aa95a7")
    suspend fun getTopMovieList(@Query("page")page:Int):Response<TopMovieModel>

    @GET("3/movie/upcoming?api_key=61691e4870c885febcaa4652a2aa95a7")
    suspend fun getUpcomingMovieList(@Query("page")page:Int):Response<UpcomingModel>

    @GET("3/trending/all/{type}")
    suspend fun getTrendingMovie(@Path("type")type:String,@Query("api_key")api:String,@Query("page")page:Int):Response<TrendingModel>

}