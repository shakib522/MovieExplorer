package com.example.movieexplorer.service.model.trendingModel


import com.google.gson.annotations.SerializedName

data class TrendingModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingResults: List<TrendingResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)