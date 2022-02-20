package com.example.movieexplorer.service.model.UpcomingModel


import com.google.gson.annotations.SerializedName

data class UpcomingModel(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val upcomingResults: List<UpcomingResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)