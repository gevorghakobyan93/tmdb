package com.movie.tmdb.model.repository.api.model.popular

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<PopularMovie> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)