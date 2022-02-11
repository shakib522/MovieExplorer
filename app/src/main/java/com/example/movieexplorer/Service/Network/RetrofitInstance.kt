package com.example.movieexplorer.Service.Network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/"
        private val gson: Gson = GsonBuilder().setLenient().create();
        private var retrofit: Retrofit? = null
        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build()
            }
            return retrofit
        }
    }
}