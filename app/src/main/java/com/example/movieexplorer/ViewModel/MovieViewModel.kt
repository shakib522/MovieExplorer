package com.example.movieexplorer.ViewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieexplorer.Service.Model.PopularModel.PopularResult
import com.example.movieexplorer.Service.Model.TopModel.Result
import com.example.movieexplorer.Service.Model.TrendingModel.TrendingResult
import com.example.movieexplorer.Service.Model.UpcomingModel.UpcomingResult
import com.example.movieexplorer.Service.Network.RetrofitInstance
import com.example.movieexplorer.Service.Repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MovieViewModel"
class MovieViewModel : ViewModel(){

    private var movieRepo: MovieRepository = MovieRepository.getInstance()
    private var popularLiveData: MutableLiveData<List<PopularResult>>?=null
    private var topLiveData: MutableLiveData<List<Result>>?=null

    fun getPopularMovieList(page: Int):MutableLiveData<List<PopularResult>>? {
        if(popularLiveData==null) {
            popularLiveData=MutableLiveData()
            fetchPopularMovie(page)
        }
        return popularLiveData
    }
    fun fetchPopularMovie(page:Int): MutableLiveData<List<PopularResult>>?{
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitInstance.api.getPopularMovieList(page)
            if (response.isSuccessful && response.body() != null) {
                val popularMovieModel = response.body()
                val popularResult = popularMovieModel?.results
                popularLiveData?.postValue(popularResult)
            }
        }
        return popularLiveData
    }

    fun getTopMovieList(page: Int): MutableLiveData<List<Result>>? {
        if(topLiveData==null){
            topLiveData=movieRepo.getTopMovieList(page)
        }
        return topLiveData
    }

    fun fetchTopMovieList(page:Int): MutableLiveData<List<Result>>?{
        topLiveData=movieRepo.fetchTopMovieList(page)
        return topLiveData
    }


    fun getUpcomingMovieList(page: Int): MutableLiveData<List<UpcomingResult>> {
        return movieRepo.getUpcomingMovieList(page)
    }

    fun getTrending(
        type: String,
        apiKey: String,
        page: Int
    ): MutableLiveData<List<TrendingResult>> {
        return movieRepo.getTrending(type, apiKey, page)
    }

}