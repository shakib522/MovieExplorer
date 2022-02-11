package com.example.movieexplorer.Service.Model.PopularModel


import com.google.gson.annotations.SerializedName

data class PopularMovieModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<PopularResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)