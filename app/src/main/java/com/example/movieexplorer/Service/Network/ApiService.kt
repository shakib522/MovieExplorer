package com.example.movieexplorer.Service.Network

import retrofit2.http.GET
import com.example.movieexplorer.Service.Model.PopularModel.PopularMovieModel
import com.example.movieexplorer.Service.Model.TopModel.TopMovieModel
import retrofit2.Call
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular?api_key=61691e4870c885febcaa4652a2aa95a7")
    fun getPopularMovieList(@Query("page")page:Int):Call<PopularMovieModel>

    @GET("3/movie/top_rated?api_key=61691e4870c885febcaa4652a2aa95a7")
    fun getTopMovieList(@Query("page")page:Int):Call<TopMovieModel>

}