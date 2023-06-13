package com.movie.tmdb.data.api

import com.movie.tmdb.data.model.PopularMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String = "en"
    ): Response<PopularMovies>
}