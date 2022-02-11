package com.example.movieexplorer.Service.Repository

import androidx.lifecycle.MutableLiveData
import com.example.movieexplorer.Service.Model.PopularModel.PopularMovieModel
import com.example.movieexplorer.Service.Model.PopularModel.PopularResult
import com.example.movieexplorer.Service.Model.TopModel.Result
import com.example.movieexplorer.Service.Model.TopModel.TopMovieModel
import com.example.movieexplorer.Service.Network.ApiService
import com.example.movieexplorer.Service.Network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MovieRepository {

    private lateinit var instance: MovieRepository
    private var mResult: List<PopularResult>?=null
    private lateinit var mLiveData: MutableLiveData<List<PopularResult>>
    private var popularMovieModel: PopularMovieModel? = null

    private var topMovieModel: TopMovieModel? = null
    private var topResult: List<Result>?=null
    private lateinit var topLiveData: MutableLiveData<List<Result>>

    fun getInstance(): MovieRepository {
        instance = MovieRepository
        return instance
    }

    fun getPopularMovieList(): MutableLiveData<List<PopularResult>> {
        mLiveData= MutableLiveData()
        val apiService = RetrofitInstance.getRetrofitInstance()?.create(ApiService::class.java)
        val call = apiService?.getPopularMovieList()
        call?.enqueue(object : Callback<PopularMovieModel> {
            override fun onResponse(
                call: Call<PopularMovieModel>,
                response: Response<PopularMovieModel>
            ) {
                if (response.body() != null) {
                    popularMovieModel = response.body()
                    mResult= popularMovieModel?.results
                }
                mLiveData.postValue(mResult)

            }

            override fun onFailure(call: Call<PopularMovieModel>, t: Throwable) {

            }

        })
        return mLiveData
    }

    fun getTopMovieList():MutableLiveData<List<Result>>{

        topLiveData= MutableLiveData()
        val apiService = RetrofitInstance.getRetrofitInstance()?.create(ApiService::class.java)
        val call = apiService?.getTopMovieList()
        call?.enqueue(object : Callback<TopMovieModel> {
            override fun onResponse(call: Call<TopMovieModel>, response: Response<TopMovieModel>) {
                if(response.body()!=null){
                    topMovieModel=response.body()
                    topResult=topMovieModel?.results
                }
                topLiveData.postValue(topResult)
            }

            override fun onFailure(call: Call<TopMovieModel>, t: Throwable) {

            }
        })
        return topLiveData
    }

}